package io.hari.demo;

import io.hari.demo.config.AppConfig;
import io.hari.demo.dao.*;
import io.hari.demo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    final AppConfig config;
    final UserDao userDao;
    final QuestionDao questionDao;
    final ContestDao contestDao;
    final TestcaseDao testcaseDao;
    final DiscussionDao discussionDao;
    final ReplyDao replyDao;

    public DemoApplication(AppConfig config,
                           UserDao userDao,
                           QuestionDao questionDao,
                           ContestDao contestDao,
                           TestcaseDao testcaseDao, DiscussionDao discussionDao, ReplyDao replyDao) {
        this.config = config;
        this.userDao = userDao;
        this.questionDao = questionDao;
        this.contestDao = contestDao;
        this.testcaseDao = testcaseDao;
        this.discussionDao = discussionDao;
        this.replyDao = replyDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("config = " + config);
        //todo: create 2 users
        User hariom = User.builder().name("hariom").build();
        User chandan = User.builder().name("chandan").build();
        userDao.saveAll(Arrays.asList(hariom, chandan));
        userDao.findAll().stream().forEach(System.out::println);

        //todo : create questions + test cases
        Question question1 = Question.builder().question("problem statement 1").build();
        Question question2 = Question.builder().question("problem statement 2").build();
        questionDao.saveAll(Arrays.asList(question1, question2));
        questionDao.findAll().stream().forEach(System.out::println);

        //		testcaseDao.saveAll(Arrays.asList(testcase1, testcase2));

        final TestCase testcase1 = TestCase.builder().expectedValue("expected output")
                .score(10)
                .build();
        final TestCase testcase2 = TestCase.builder().expectedValue("expected output 2")
                .score(10)
                .build();
        testcaseDao.saveAll(Arrays.asList(testcase1, testcase2));
        question1.setTestCases(Arrays.asList(testcase1, testcase2));
		questionDao.save(question1);//not required since we added cascase all
        questionDao.findAll().stream().forEach(System.out::println);

        //todo : create contest - add 2 questions
        Contest contest1 = Contest.builder().starDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusDays(2))
                .build();
        contestDao.save(contest1);
        contest1.setQuestions(Arrays.asList(question1, question2));
        contestDao.save(contest1);
        contestDao.findAll().stream().forEach(System.out::println);

        //todo : user can submit single questions
        final String javaCode = "expected output";
        AtomicInteger finalScore = new AtomicInteger();
        question1.getTestCases().stream().forEach(t -> {
            final int calculateScore = t.calculateScore(javaCode);
            finalScore.addAndGet(calculateScore);
        });

        final QuestionSolution questionSolution1 = QuestionSolution.builder()
                                                    .questionId(question1.getId())
                                                    .score(finalScore.get())
                                                    .myCode(javaCode).build();
        System.out.println("questionSolution1 = " + questionSolution1);
        hariom.setUserQuestions(Arrays.asList(UserQuestion.builder().questionSolution(questionSolution1).build()));
        userDao.save(hariom);
        final List<UserQuestion> myCode = hariom.getUserQuestions();
        myCode.forEach(userQuestion -> {
            final QuestionSolution questionSolution = userQuestion.getQuestionSolution();
            final Long questionId = questionSolution.getQuestionId();
            final String myCode1 = questionSolution.getMyCode();
            System.out.println("questionId = " + questionId);
            System.out.println("myCode1 = " + myCode1);
        });

        //todo : user can subscribe contest
        hariom.setSubscribeContests(Arrays.asList(contest1));
        userDao.save(hariom);
        final List<Contest> contests = userDao.findById(hariom.getId()).get().getSubscribeContests();
        System.out.println("contests = " + contests);

        //todo : user can start question inside contest
        final List<Contest> subscribeContests = hariom.getSubscribeContests();
        final Contest contest = subscribeContests.get(0);
        final List<Question> questions = contest.getQuestions();
        List<UserContest> list = new ArrayList<>();
        List<QuestionSolution> questionSolutions = new ArrayList<>();
        questions.forEach(q -> {
            final Question fetchedQuestion = q;
            QuestionSolution questionSolution = QuestionSolution.builder().questionId(fetchedQuestion.getId()).myCode("my python code").build();
            questionSolutions.add(questionSolution);
        });
        final ContestQuestionSolution contestQuestionSolution = ContestQuestionSolution.builder().questionSolutions(questionSolutions).build();
        final UserContest userContest = UserContest.builder().contestId(contest.getId()).questionSolution(contestQuestionSolution).build();
        list.add(userContest);
        hariom.setUserContestsSolution(list);
        userDao.save(hariom);

        //todo : user 1 - post a discussion  for question
        final Discussion discussion = Discussion.builder().title("discussion title").body("discussion body").build();
        discussionDao.save(discussion);
        question1.setDiscussions(Arrays.asList(discussion));
        questionDao.save(question1);

        //todo : user 2 - reply above discussion
        discussion.setReplies(Arrays.asList(Reply.builder().userId(chandan.getId()).reply("chandan reply").build()));
        discussionDao.save(discussion);
        final Reply hariom_reply = Reply.builder().userId(hariom.getId()).reply("hariom reply").build();
        replyDao.save(hariom_reply);

        //todo : add new reply , delete all old then set old + new (m2 solution make jpa bidirectional)
        discussion.setReplies(null);
        discussion.setReplies(Arrays.asList(replyDao.findById(1L).get(), replyDao.findById(2L).get()));
        discussionDao.save(discussion);
    }
}

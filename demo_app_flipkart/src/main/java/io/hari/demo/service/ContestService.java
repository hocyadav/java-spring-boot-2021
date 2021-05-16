package io.hari.demo.service;

import io.hari.demo.constant.Level;
import io.hari.demo.dao.ContestDao;
import io.hari.demo.dao.QuestionDao;
import io.hari.demo.dao.UserDao;
import io.hari.demo.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestService {
    private final ContestDao contestDao;
    private final QuestionDao questionDao;
    private final UserDao userDao;

    public Contest create(String contestName, Level contestLevel, User user) {
        final List<Long> questions = assignAllContestQuestions(contestLevel);

        final Contest contest = Contest.builder().name(contestName)
                .userId(user.getId())
                .level(contestLevel)
                .contestQuestions(ContestQuestions.builder().questions(questions).build())
                .build();
        final Contest contest1 = contestDao.save(contest);

        final User optionalUser = userDao.findById(user.getId()).get();
        optionalUser.setContests(Arrays.asList(contest));

        optionalUser.setContestQuestions(contest1.getContestQuestions());
        userDao.save(optionalUser);

        return contest1;
    }

    private List<Long> assignAllContestQuestions(Level contestLevel) {
        final List<Question> questions = questionDao.findAllByLevelQuestions(contestLevel.toString());
        final List<Long> questionIds = questions.stream().map(i -> i.getId()).collect(Collectors.toList());
        return questionIds;
    }

    public void runContest(Contest contest) {
        final List<User> allByContestsId = userDao.findAllByContestsId(contest.getUserId());

        //for all user solve question
        allByContestsId.forEach(user -> {
            final ContestQuestions contestQuestions = user.getContestQuestions();
            System.out.println("contestQuestions = " + contestQuestions);
            final List<Long> questions = contestQuestions.getQuestions();
            //calculate score
        });
    }

}

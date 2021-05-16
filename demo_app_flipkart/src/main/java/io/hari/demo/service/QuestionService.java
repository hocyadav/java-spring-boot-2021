package io.hari.demo.service;

import io.hari.demo.dao.QuestionDao;
import io.hari.demo.constant.Level;
import io.hari.demo.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionDao questionDao;

    public List<Question> getAllQuestionLevelWise(final Level questionLevel) {
        final List<Question> allByLevel = questionDao.findAllByLevelQuestions(questionLevel.toString());
        return allByLevel;
    }

}

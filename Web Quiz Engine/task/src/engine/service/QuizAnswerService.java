package engine.service;

import engine.model.Answer;
import engine.model.QuizAnswer;

import java.util.Optional;

public interface QuizAnswerService {

    void save(QuizAnswer quizAnswer);
    Optional<Answer> solve(Long quizId, Long answerOptionId);
}

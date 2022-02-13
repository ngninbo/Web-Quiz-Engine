package engine.service;

import engine.model.Answer;
import engine.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Optional<Quiz> findQuizById(Long id);
    Quiz save(Quiz quiz);
    List<Quiz> findAll();
    Optional<Answer> solve(Long quizId, List<Long> answer);
}

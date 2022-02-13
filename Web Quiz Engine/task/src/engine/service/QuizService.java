package engine.service;

import engine.dto.QuizDto;
import engine.model.Answer;
import engine.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Optional<Quiz> findQuizById(Long id);
    Optional<QuizDto> findById(Long id);

    QuizDto save(Quiz quiz);

    List<QuizDto> findAll();

    Optional<Answer> solve(Long quizId, List<Long> answer);

    void deleteById(long quizId);

    QuizDto update(Quiz quiz);
}

package engine.service;

import engine.dto.QuizDto;
import engine.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Optional<Quiz> findQuizById(Long id);
    Quiz save(QuizDto quizDto);
    List<Quiz> findAll();
}

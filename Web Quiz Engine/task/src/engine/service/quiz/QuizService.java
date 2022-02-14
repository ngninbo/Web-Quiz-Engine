package engine.service.quiz;

import engine.dto.QuizDto;
import engine.model.Answer;
import engine.model.Quiz;
import engine.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Optional<Quiz> findQuizById(Long id);
    Optional<QuizDto> findById(Long id);

    QuizDto save(Quiz quiz);

    Page<Quiz> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Optional<Answer> solve(Long quizId, User user, List<Long> answer);

    void deleteById(long quizId);

    QuizDto update(Quiz quiz);
}

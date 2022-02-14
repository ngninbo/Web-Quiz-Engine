package engine.repository;

import engine.model.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {

    Optional<Quiz> findById(Long id);
}

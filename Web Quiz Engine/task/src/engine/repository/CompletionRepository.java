package engine.repository;

import engine.model.Completion;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletionRepository extends PagingAndSortingRepository<Completion, Long> {
    Page<Completion> findAllByUser(User user, Pageable pageable);
}

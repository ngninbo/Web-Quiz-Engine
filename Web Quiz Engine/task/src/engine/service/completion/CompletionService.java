package engine.service.completion;

import engine.model.Completion;
import engine.model.User;
import org.springframework.data.domain.Page;

public interface CompletionService {

    Page<Completion> findAllByUser(User user, Integer pageNo, Integer pageSize);
}

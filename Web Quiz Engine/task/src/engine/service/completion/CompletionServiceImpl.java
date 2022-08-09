package engine.service.completion;

import engine.model.Completion;
import engine.model.User;
import engine.repository.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unused"})
public class CompletionServiceImpl implements CompletionService {

    private final CompletionRepository completionRepository;

    @Autowired
    public CompletionServiceImpl(CompletionRepository completionRepository) {
        this.completionRepository = completionRepository;
    }

    @Override
    public Page<Completion> findAllByUser(User user, Integer pageNo, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("completedAt").descending());

        Page<Completion> pagedResult = completionRepository.findAllByUser(user, paging);

        return pagedResult.hasContent() ? pagedResult : Page.empty();
    }
}

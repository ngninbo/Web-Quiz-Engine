package engine.repository;

import engine.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class QuizRepository {
    
    private final Map<Long, Quiz> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public QuizRepository() {
        super();
    }
    
    public Optional<Quiz> findBy(Long id) {

        Quiz quiz = storage.get(id);

        return quiz == null ? Optional.empty() : Optional.of(quiz);
    }
    
    public Quiz save(Quiz quiz) {

        long id = idGenerator.incrementAndGet();
        quiz.setId(id);
        storage.putIfAbsent(id, quiz);

        return quiz;
    }

    public List<Quiz> findAll() {
        return storage.isEmpty() ? List.of() : new ArrayList<>(storage.values());
    }
}

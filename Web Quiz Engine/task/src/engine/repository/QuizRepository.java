package engine.repository;

import engine.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QuizRepository {
    
    private final Map<Long, Quiz> quizMap = new ConcurrentHashMap<>();

    public QuizRepository() {
        super();
    }
    
    public Optional<Quiz> findBy(Long id) {
        return quizMap.isEmpty() || !quizMap.containsKey(id) ? Optional.empty() : Optional.of(quizMap.get(id));

    }
    
    public Quiz save(Quiz quiz) {
        quiz.setId(quizMap.size() + 1L);
        quizMap.put(quiz.getId(), quiz);
        return quiz;
    }

    public List<Quiz> findAll() {
        
        if (quizMap.isEmpty()) {
            return List.of();
        } else {
            return new ArrayList<>(quizMap.values());
        }
    }
}

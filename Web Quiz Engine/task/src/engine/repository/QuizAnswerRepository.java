package engine.repository;

import engine.model.QuizAnswer;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class QuizAnswerRepository {

    private final List<QuizAnswer> quizAnswers = new LinkedList<>();

    public QuizAnswerRepository() {
        super();
    }

    public void save(QuizAnswer quizAnswer) {
        quizAnswers.add(quizAnswer);
    }

    public Optional<Long> findAnswerQuiz(Long id) {
        return quizAnswers.stream()
                .filter(quizAnswer -> Objects.equals(quizAnswer.getQuizId(), id))
                .findFirst()
                .map(QuizAnswer::getAnswerId);
    }
}

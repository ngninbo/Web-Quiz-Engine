package engine.service;

import engine.model.Answer;
import engine.model.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findBy(id);
    }

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Answer> solve(Long quizId, List<Long> answer) {

        Optional<Quiz> optionalQuiz = quizRepository.findBy(quizId);

        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            return quiz.getAnswer() == null && answer.isEmpty() ||
                    Objects.equals(answer, optionalQuiz.get().getAnswer()) ?
                    Optional.of(Answer.success()) : Optional.of(Answer.failure());
        }

        return Optional.empty();
    }
}

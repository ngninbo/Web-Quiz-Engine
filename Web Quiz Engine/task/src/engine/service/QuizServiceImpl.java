package engine.service;

import engine.dto.QuizDto;
import engine.mapper.QuizMapper;
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
    private final QuizMapper quizMapper;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Optional<QuizDto> findById(Long id) {
        return Optional.of(quizMapper.toQuizDto(quizRepository.findById(id).orElseThrow()));
    }

    @Override
    public QuizDto save(Quiz quiz) {
        return quizMapper.toQuizDto(quizRepository.save(quiz));
    }

    @Override
    public List<QuizDto> findAll() {
        return quizMapper.toQuizzesDtos(quizRepository.findAll());
    }

    @Override
    public Optional<Answer> solve(Long quizId, List<Long> answer) {

        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);

        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            return quiz.getAnswer() == null && answer.isEmpty() ||
                    Objects.equals(answer, quiz.getAnswer()) ?
                    Optional.of(Answer.success()) : Optional.of(Answer.failure());
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public QuizDto update(Quiz quiz) {
        return quizMapper.toQuizDto(quizRepository.save(quiz));
    }
}

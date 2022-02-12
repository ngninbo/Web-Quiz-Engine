package engine.service;

import engine.dto.QuizDto;
import engine.mapper.QuizMapper;
import engine.model.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return quizRepository.findBy(id);
    }

    @Override
    public Quiz save(QuizDto quizDto) {
        return quizRepository.save(quizMapper.toQuiz(quizDto));
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
}

package engine.service.quiz;

import engine.dto.QuizDto;
import engine.mapper.EntityMapper;
import engine.model.Answer;
import engine.model.Completion;
import engine.model.Quiz;
import engine.model.User;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CompletionRepository completionRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository,
                           CompletionRepository completionRepository) {
        this.quizRepository = quizRepository;
        this.completionRepository = completionRepository;
    }

    @Override
    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Optional<QuizDto> findById(Long id) {
        return Optional.of(EntityMapper.toQuizDto(quizRepository.findById(id).orElseThrow()));
    }

    @Override
    public QuizDto save(Quiz quiz) {
        return EntityMapper.toQuizDto(quizRepository.save(quiz));
    }

    @Override
    public Page<Quiz> findAll(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Quiz> pagedResult = quizRepository.findAll(paging);

        return pagedResult.hasContent() ? pagedResult : Page.empty();
    }

    @Override
    public Optional<Answer> solve(Long quizId, User user, List<Long> answer) {

        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);

        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();

            if (quiz.getAnswer() == null && answer.isEmpty() || Objects.equals(answer, quiz.getAnswer())) {
                Completion completion = new Completion(user, quiz);
                this.completionRepository.save(completion);
                return Optional.of(Answer.success());
            } else {
                return Optional.of(Answer.failure());
            }
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public QuizDto update(Quiz quiz) {
        return EntityMapper.toQuizDto(quizRepository.save(quiz));
    }
}

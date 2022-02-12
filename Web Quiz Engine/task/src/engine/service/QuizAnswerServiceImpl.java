package engine.service;

import engine.model.Answer;
import engine.model.QuizAnswer;
import engine.repository.QuizAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizAnswerServiceImpl implements QuizAnswerService {

    private final QuizAnswerRepository quizAnswerRepository;

    @Autowired
    public QuizAnswerServiceImpl(QuizAnswerRepository quizAnswerRepository) {
        this.quizAnswerRepository = quizAnswerRepository;
    }

    @Override
    public void save(QuizAnswer quizAnswer) {
        quizAnswerRepository.save(quizAnswer);
    }

    @Override
    public Optional<Answer> solve(Long quizId, Long answerOptionId) {
        return quizAnswerRepository.findAnswerQuiz(quizId)
                .flatMap(aLong -> aLong.equals(answerOptionId) ?
                        Optional.of(new Answer(true, "Congratulations, you're right!")) :
                        Optional.of(new Answer(false, "Wrong answer! Please, try again."))
                );

    }
}

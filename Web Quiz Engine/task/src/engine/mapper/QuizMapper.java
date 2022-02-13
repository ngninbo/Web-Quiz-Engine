package engine.mapper;

import engine.model.Quiz;
import engine.dto.QuizDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizMapper {

    public QuizDto toQuizDto(Quiz quiz) {
        return new QuizDto(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    public List<QuizDto> toQuizzesDtos(List<Quiz> quizzes) {
        return quizzes.stream().map(quiz -> new QuizDto(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions()))
                .collect(Collectors.toList());
    }
}

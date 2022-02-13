package engine.mapper;

import engine.model.Quiz;
import engine.dto.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizDto toQuizDto(Quiz quiz) {
        return new QuizDto(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }
}

package engine.mapper;

import engine.dto.QuizDto;
import engine.model.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public Quiz toQuiz(QuizDto quizDto) {
        return new Quiz(quizDto.getTitle(), quizDto.getText(), quizDto.getOptions());
    }
}

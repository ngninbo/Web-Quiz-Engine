package engine.mapper;

import engine.dto.CompletionDto;
import engine.model.Completion;
import engine.model.Quiz;
import engine.dto.QuizDto;

public class EntityMapper {

    public static QuizDto toQuizDto(Quiz quiz) {
        return new QuizDto(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    public static CompletionDto completionDto(Completion completion) {
        return new CompletionDto(completion.getQuiz().getId(), completion.getCompletedAt());
    }
}

package engine.mapper;

import engine.dto.CompletionDto;
import engine.model.Completion;
import engine.model.Quiz;
import engine.dto.QuizDto;

public class EntityMapper {

    /**
     * Map Quiz entity to DTO
     * @param quiz Quiz
     * @return QuizDto
     */
    public static QuizDto toDto(Quiz quiz) {
        return new QuizDto(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    /**
     * Map Completion entity to DTO
     * @param completion Completion
     * @return CompletionDto
     */
    public static CompletionDto toDto(Completion completion) {
        return new CompletionDto(completion.getQuiz().getId(), completion.getCompletedAt());
    }
}

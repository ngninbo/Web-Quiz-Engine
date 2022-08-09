package engine.dto;

import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings({"unused"})
public class CompletionDto {

    private Long id;
    private LocalDateTime completedAt;

    public CompletionDto() {
        super();
    }

    public CompletionDto(Long id, LocalDateTime completedAt) {
        this();
        this.id = id;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompletionDto)) return false;
        CompletionDto that = (CompletionDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCompletedAt(), that.getCompletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompletedAt());
    }

    @Override
    public String toString() {
        return "CompletionDto{" +
                "quizId=" + id +
                ", completedAt=" + completedAt +
                '}';
    }
}

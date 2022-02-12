package engine.model;

import java.util.Objects;

public class QuizAnswer {

    private Long quizId;
    private Long answerId;

    public QuizAnswer() {
        super();
    }

    public QuizAnswer(Long quizId, Long answerId) {
        this.quizId = quizId;
        this.answerId = answerId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizAnswer)) return false;
        QuizAnswer that = (QuizAnswer) o;
        return Objects.equals(getQuizId(), that.getQuizId()) && Objects.equals(getAnswerId(), that.getAnswerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuizId(), getAnswerId());
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
                "quizId=" + quizId +
                ", answerId=" + answerId +
                '}';
    }
}

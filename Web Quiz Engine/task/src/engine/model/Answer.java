package engine.model;

import java.util.Objects;

public class Answer {

    private boolean success;
    private String feedback;

    public Answer(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return isSuccess() == answer.isSuccess() && Objects.equals(getFeedback(), answer.getFeedback());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccess(), getFeedback());
    }

    @Override
    public String toString() {
        return "Answer{" +
                "success=" + success +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}

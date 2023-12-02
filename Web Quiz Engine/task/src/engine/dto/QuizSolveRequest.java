package engine.dto;

import java.util.List;

@SuppressWarnings({"unused"})
public class QuizSolveRequest {

    private List<Long> answer;

    public QuizSolveRequest() {
        super();
    }

    public QuizSolveRequest(List<Long> answer) {
        this.answer = answer;
    }

    public List<Long> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Long> answer) {
        this.answer = answer;
    }
}

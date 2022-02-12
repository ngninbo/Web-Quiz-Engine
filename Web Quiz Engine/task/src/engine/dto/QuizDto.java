package engine.dto;

import java.util.List;
import java.util.Objects;

public class QuizDto {

    private String title;
    private String text;
    private List<String> options;
    private Long answer;

    public QuizDto() {
        super();
    }

    public QuizDto(String title, String text, List<String> options, Long answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizDto)) return false;
        QuizDto quizDto = (QuizDto) o;
        return Objects.equals(getTitle(), quizDto.getTitle()) && Objects.equals(getText(), quizDto.getText()) && Objects.equals(getOptions(), quizDto.getOptions()) && Objects.equals(getAnswer(), quizDto.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getText(), getOptions(), getAnswer());
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}

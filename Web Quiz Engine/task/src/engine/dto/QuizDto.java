package engine.dto;

import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused"})
public class QuizDto {

    private Long id;
    private String title;
    private String text;
    private List<String> options;

    public QuizDto(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public QuizDto(Long id, String title, String text, List<String> options) {
        this(title, text, options);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizDto)) return false;
        QuizDto quizDto = (QuizDto) o;
        return Objects.equals(getId(), quizDto.getId()) && Objects.equals(getTitle(), quizDto.getTitle()) &&
                Objects.equals(getText(), quizDto.getText()) && Objects.equals(getOptions(), quizDto.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getText(), getOptions());
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}

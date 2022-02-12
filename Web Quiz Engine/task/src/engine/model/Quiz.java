package engine.model;

import java.util.List;
import java.util.Objects;


public class Quiz {

    private Long id;
    private String title;
    private String text;
    private List<String> options;

    public Quiz(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiz)) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(getId(), quiz.getId()) && Objects.equals(getTitle(), quiz.getTitle()) && Objects.equals(getText(), quiz.getText()) && Objects.equals(getOptions(), quiz.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getText(), getOptions());
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}

package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quiz")
@SequenceGenerator(name = "seq", sequenceName = "quizSequence")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @NotNull(message = "Quiz must have a title")
    @NotEmpty(message = "Quiz title should not be empty")
    private String title;

    @NotNull(message = "Quiz must have a title")
    @NotEmpty(message = "Quiz title should not be empty")
    @Column(name = "question")
    private String text;

    @NotNull(message = "The options must not be null")
    @Size(min = 2, message = "Quiz must contain at least two options")
    @ElementCollection
    @Column(name = "options")
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @Column(name = "answer")
    private List<Long> answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Quiz() {
        super();
    }

    public Quiz(String title, String text, List<String> options, List<Long> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(Long id, String title, String text, List<String> options, List<Long> answer) {
        this(title, text, options, answer);
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

    public List<Long> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Long> answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiz)) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(getId(), quiz.getId()) && Objects.equals(getTitle(), quiz.getTitle()) &&
                Objects.equals(getText(), quiz.getText()) && Objects.equals(getOptions(), quiz.getOptions()) &&
                Objects.equals(getAnswer(), quiz.getAnswer()) && Objects.equals(getUser(), quiz.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getText(), getOptions(), getAnswer(), getUser());
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", user=" + user +
                '}';
    }
}

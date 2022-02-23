package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "completion")
@SequenceGenerator(name = "compSeq", sequenceName = "CompSeq")
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compSeq")
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "quiz_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Quiz quiz;

    @CreationTimestamp
    private final LocalDateTime completedAt;

    {
        completedAt = LocalDateTime.now();
    }

    public Completion() {
        super();
    }

    public Completion(User user, Quiz quiz) {
        this();
        this.user = user;
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Completion)) return false;
        Completion that = (Completion) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getQuiz(), that.getQuiz()) &&
                Objects.equals(getCompletedAt(), that.getCompletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getQuiz(), getCompletedAt());
    }

    @Override
    public String toString() {
        return "Completion{" +
                "id=" + id +
                ", user=" + user +
                ", quiz=" + quiz +
                ", completedAt=" + completedAt +
                '}';
    }
}

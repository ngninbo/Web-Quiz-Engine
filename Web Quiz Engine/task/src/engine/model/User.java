package engine.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@SequenceGenerator(name = "userSeq", sequenceName = "UserSeq")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;

    @NotNull
    @NotBlank
    @Email
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 5)
    private String password;

    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Quiz> quizzes;

    public User() {
        super();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword()) && getRole().equals(user.getRole()) &&
                getQuizzes().equals(user.getQuizzes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getRole(), getQuizzes());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}

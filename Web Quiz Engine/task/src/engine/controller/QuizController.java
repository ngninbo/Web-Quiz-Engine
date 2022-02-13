package engine.controller;

import engine.dto.QuizDto;
import engine.model.Quiz;
import engine.model.Answer;
import engine.model.User;
import engine.service.QuizService;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/quizzes", produces = APPLICATION_JSON_VALUE)
@Validated
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;
    private final BiFunction<String, String, Boolean> isAuthor = String::equals;

    @Autowired
    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QuizDto> findById(@AuthenticationPrincipal UserDetails userDetails,
                                         @PathVariable String id) {
        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<QuizDto> quiz = quizService.findById(Long.parseLong(id));
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizDto> addQuiz(@AuthenticationPrincipal UserDetails userDetails,
                                        @Valid @RequestBody Quiz quiz) {
        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Optional<User> user = userService.findByEmail(userDetails.getUsername());
            quiz.setUser(user.orElseThrow());
            return new ResponseEntity<>(quizService.save(quiz), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizDto> updateQuiz(@AuthenticationPrincipal UserDetails userDetails,
                                           @Valid @RequestBody Quiz quiz) {
        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<User> user = userService.findByEmail(userDetails.getUsername());
        quiz.setUser(user.orElseThrow());

        return new ResponseEntity<>(quizService.update(quiz), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/solve")
    public ResponseEntity<Answer> solve(@AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable String id, @RequestBody Map<String, List<Long>> answer) {
        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Answer> optionalAnswer = quizService.solve(Long.parseLong(id), answer.get("answer"));
        return optionalAnswer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<List<QuizDto>> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.isEnabled() ? new ResponseEntity<>(quizService.findAll(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quiz> deleteById(@AuthenticationPrincipal UserDetails userDetails,
                                                @PathVariable String id) {
        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Optional<Quiz> quiz = quizService.findQuizById(Long.parseLong(id));
            if (quiz.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                if (!isAuthor.apply(quiz.get().getUser().getEmail(), userDetails.getUsername())) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                } else {
                    quizService.deleteById(Long.parseLong(id));
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(Exception exception) {
        return new ResponseEntity<>("Element not found: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}

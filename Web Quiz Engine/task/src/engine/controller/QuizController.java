package engine.controller;

import engine.dto.CompletionDto;
import engine.dto.QuizDto;
import engine.mapper.EntityMapper;
import engine.model.Quiz;
import engine.model.Answer;
import engine.model.User;
import engine.service.completion.CompletionService;
import engine.service.quiz.QuizService;
import engine.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/quizzes", produces = APPLICATION_JSON_VALUE)
@Validated
@SuppressWarnings({"unused"})
public class QuizController {

    private final QuizService quizService;
    private final CompletionService completionService;
    private final UserService userService;

    @Autowired
    public QuizController(QuizService quizService,
                          CompletionService completionService,
                          UserService userService) {
        this.quizService = quizService;
        this.completionService = completionService;
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QuizDto> findById(@AuthenticationPrincipal UserDetails userDetails,
                                         @PathVariable String id) {
        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return quizService.findById(Long.parseLong(id)).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
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
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        Optional<Answer> optionalAnswer = quizService.solve(Long.parseLong(id), user, answer.get("answer"));

        return optionalAnswer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<Page<QuizDto>> findAll(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                 @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        return userDetails.isEnabled() ?
                new ResponseEntity<>(quizService.findAll(page, pageSize, sortBy)
                        .map(EntityMapper::toDto), new HttpHeaders(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<CompletionDto>> findAllCompleted(@AuthenticationPrincipal UserDetails userDetails,
                                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                                @RequestParam(defaultValue = "0") Integer page) {

        if (userDetails.isEnabled()) {
            User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
            return new ResponseEntity<>(completionService.findAllByUser(user, page, pageSize)
                    .map(EntityMapper::toDto), new HttpHeaders(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
                if (!quiz.get().getUser().compareEmail(userDetails.getUsername())) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                } else {
                    quizService.deleteById(Long.parseLong(id));
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
            IllegalArgumentException.class,
            InvalidDataAccessResourceUsageException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(String.format("A %s occur: %s", e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(Exception exception) {
        return new ResponseEntity<>("Element not found: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}

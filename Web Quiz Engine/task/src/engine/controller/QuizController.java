package engine.controller;

import engine.dto.QuizDto;
import engine.model.Answer;
import engine.model.Quiz;
import engine.model.QuizAnswer;
import engine.service.QuizAnswerService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;
    private final QuizAnswerService quizAnswerService;

    @Autowired
    public QuizController(QuizService quizService, QuizAnswerService quizAnswerService) {
        this.quizService = quizService;
        this.quizAnswerService = quizAnswerService;
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> findById(@PathVariable String id) {
        Optional<Quiz> quiz = quizService.findQuizById(Long.parseLong(id));
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> update(@RequestBody QuizDto quizDto) {
        Quiz quiz = quizService.save(quizDto);
        quizAnswerService.save(new QuizAnswer(quiz.getId(), quizDto.getAnswer()));
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<Answer> solve(@PathVariable String id, @RequestParam String answer) {
        Optional<Answer> optionalAnswer = quizAnswerService.solve(Long.parseLong(id), Long.parseLong(answer));
        return optionalAnswer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<Quiz>> findAll() {
        return new ResponseEntity<>(quizService.findAll(), HttpStatus.OK);
    }
}

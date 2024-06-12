package pro.sky.coursework2.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/java")
@RequiredArgsConstructor
public class JavaQuestionController {
    private final QuestionService questionService;

    @PostMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/find")
    public Question findQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.findQuestion(question, answer);
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestBody Question question) {
        return questionService.remove(question);
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}

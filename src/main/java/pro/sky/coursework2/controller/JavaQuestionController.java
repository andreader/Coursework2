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
    public Question addQuestion(@RequestBody Question question) {
        return questionService.add(question.getQuestion(), question.getAnswer());
    }

    @GetMapping("/find")
    public Question findQuestion(@RequestBody Question question) {
        return questionService.findQuestion(question.getQuestion(), question.getAnswer());
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

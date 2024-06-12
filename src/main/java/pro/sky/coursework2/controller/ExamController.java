package pro.sky.coursework2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExaminerService examinerService;

    @GetMapping(path = "/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}

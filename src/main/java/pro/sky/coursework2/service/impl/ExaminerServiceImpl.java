package pro.sky.coursework2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.exception.QuestionStorageLessThanRequested;
import pro.sky.coursework2.exception.ZeroArgumentException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.ExaminerService;
import pro.sky.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 0) {
            throw new ZeroArgumentException("Количество вопросов должно быть больше нуля!");
        }
        if (amount > questionService.getAll().size()) {
            throw new QuestionStorageLessThanRequested("Requested more questions than stored in service!");
        }
        return IntStream.range(1, amount + 1)
                .mapToObj(i -> questionService.getRandomQuestion())
                .toList();
    }
}

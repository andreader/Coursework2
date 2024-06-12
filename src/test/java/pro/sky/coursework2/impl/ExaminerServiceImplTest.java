package pro.sky.coursework2.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2.exception.QuestionStorageLessThanRequested;
import pro.sky.coursework2.exception.ZeroArgumentException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;
import pro.sky.coursework2.service.impl.ExaminerServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setUp() {
        examinerService = new ExaminerServiceImpl(questionService);
    }


    @Test
    public void getQuestions_whenAmountIsNegative_shouldThrowZeroArgumentException() {
        // given
        int amount = -1;

        // when
        // then
        assertThrows(ZeroArgumentException.class, () -> examinerService.getQuestions(amount));
    }


    @Test
    public void getQuestions_whenAmountLessThanStored_shouldReturnQuestions() {
        // given
        int amount = 2;
        when(questionService.getAll()).thenReturn(List.of(
                new Question("вопрос1", "ответ1"),
                new Question("вопрос2", "ответ2"),
                new Question("вопрос3", "ответ3")));

        // when
        Collection<Question> questions = examinerService.getQuestions(amount);

        // then
        assertThat(questions.size()).isEqualTo(amount);
    }

    @Test
    public void getQuestions_whenAmountMoreThanStored_shouldThrowException() {
        // given
        int amount = 4;
        when(questionService.getAll()).thenReturn(List.of(
                new Question("вопрос1", "ответ1"),
                new Question("вопрос2", "ответ2"),
                new Question("вопрос3", "ответ3")));

        // when and then
        assertThatThrownBy(() -> examinerService.getQuestions(amount))
                .isInstanceOf(QuestionStorageLessThanRequested.class)
                .hasMessageContaining("Requested more questions than stored in service!");
    }
}



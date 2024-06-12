package pro.sky.coursework2.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.impl.JavaQuestionServiceImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JavaQuestionServiceImplTest {

    private final JavaQuestionServiceImpl service = new JavaQuestionServiceImpl();
    private final Set<Question> questions = new HashSet<>();


    // Проверка метода add(String question, String answer)
    @Test
    @DisplayName("Should add a new question")
    void shouldAddANewQuestion() {
        // given
        String question = "вопрос1";
        String answer = "ответ1";

        // when
        Question addedQuestion = service.add(question, answer);

        // then
        assertThat(addedQuestion).isNotNull();
        assertThat(addedQuestion.getQuestion()).isEqualTo(question);
        assertThat(addedQuestion.getAnswer()).isEqualTo(answer);
        assertThat(service.getAll()).contains(addedQuestion);
    }

    // Проверка метода add(Question question)
    @Test
    @DisplayName("Should add a new question")
    void shouldAddANewQuestionWithQuestionObject() {
        // given
        Question question = new Question("вопрос1", "ответ1");

        // when
        Question addedQuestion = service.add(question);

        // then
        assertThat(addedQuestion).isNotNull();
        assertThat(addedQuestion).isEqualTo(question);
        assertThat(service.getAll()).contains(addedQuestion);
    }

    // Проверка метода getAll()
    @Test
    @DisplayName("Should return all questions")
    void shouldReturnAllQuestions() {
        // given
        service.add(new Question("вопрос1", "ответ1"));
        service.add(new Question("вопрос2", "ответ2"));


        // when
        Collection<Question> allQuestions = service.getAll();

        // then
        assertThat(allQuestions).isNotNull();
        assertThat(allQuestions).hasSize(2);
        assertThat(allQuestions).containsAll(questions);
    }

    // Проверка метода remove(Question question)
    @Test
    @DisplayName("Should remove a question")
    void shouldRemoveAQuestion() {
        // given
        Question question = new Question("вопрос1", "ответ1");
        service.add(question);

        // when
        Question removedQuestion = service.remove(question);

        // then
        assertThat(removedQuestion).isNotNull();
        assertThat(removedQuestion).isEqualTo(question);
        assertThat(service.getAll()).doesNotContain(question);
    }

    // Проверка метода findQuestion(String question, String answer)
    @Test
    @DisplayName("Should find a question by question and answer")
    void shouldFindAQuestionByQuestionAndAnswer() {
        // given
        Question question = new Question("вопрос1", "ответ1");
        service.add(question);

        // when
        Question foundQuestion = service.findQuestion(question.getQuestion(), question.getAnswer());

        // then
        assertThat(foundQuestion).isNotNull();
        assertThat(foundQuestion).isEqualTo(question);
    }

    // Проверка метода findQuestion(String question, String answer) с использованием исключения
    @Test
    @DisplayName("Should throw an exception when question not found")
    void shouldThrowAnExceptionWhenQuestionNotFound() {
        // given
        // when
        assertThrows(NoSuchElementException.class, () -> service.findQuestion("несуществующий вопрос", "несуществующий ответ"));
    }

}

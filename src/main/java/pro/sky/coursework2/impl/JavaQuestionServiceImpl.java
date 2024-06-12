package pro.sky.coursework2.impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionServiceImpl implements QuestionService {
    final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Question findQuestion(String question, String answer) {
        return questions.stream()
                .filter(q -> q.getQuestion().equals(question) && q.getAnswer().equals(answer))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No question found"));
    }

    @Override
    public Question getRandomQuestion() {
        return questions.stream()
                .skip(random.nextInt(questions.size()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No question found"));
    }
}



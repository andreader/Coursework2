package pro.sky.coursework2.service;

import pro.sky.coursework2.model.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Collection<Question> getAll();

    Question remove(Question question);

    Question findQuestion(String question, String answer);

    Question getRandomQuestion();
}

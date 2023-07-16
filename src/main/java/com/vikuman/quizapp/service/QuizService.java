package com.vikuman.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vikuman.quizapp.dao.QuestionDao;
import com.vikuman.quizapp.dao.QuizDao;
import com.vikuman.quizapp.model.Question;
import com.vikuman.quizapp.model.QuestionWrapper;
import com.vikuman.quizapp.model.Quiz;
import com.vikuman.quizapp.model.Response;

import java.util.ArrayList;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String quizName) {

        Quiz quiz = new Quiz();
        quiz.setTitle(quizName);
        quiz.setQuestion(questionDao.getRandomQuizQuestinonsWithCategory(category, numQ));
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestion();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question question : questionsFromDb) {
            questionForUser.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(),
                    question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()));
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> response) {
        List<Question> questionsFromDb = quizDao.findById(id).get().getQuestion();
        int Score = 0;
        for (Question question : questionsFromDb) {
            for (Response res : response) {
                if (question.getId() == res.getId()) {
                    if (res.getResponse().equals(question.getRightAnswer())) {
                        Score++;
                    }
                }
            }
        }
        return new ResponseEntity<Integer>(Score, HttpStatus.OK);
    }

}

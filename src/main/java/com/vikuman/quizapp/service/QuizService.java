package com.vikuman.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vikuman.quizapp.dao.QuestionDao;
import com.vikuman.quizapp.dao.QuizDao;
import com.vikuman.quizapp.model.Question;
import com.vikuman.quizapp.model.Quiz;

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

}

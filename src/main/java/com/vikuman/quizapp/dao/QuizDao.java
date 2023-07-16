package com.vikuman.quizapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.vikuman.quizapp.model.Question;
import com.vikuman.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}

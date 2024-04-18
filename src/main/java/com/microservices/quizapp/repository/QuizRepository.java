package com.microservices.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.quizapp.question.Quiz;

@Repository

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	

}

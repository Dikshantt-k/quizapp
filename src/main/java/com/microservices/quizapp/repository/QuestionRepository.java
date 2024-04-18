package com.microservices.quizapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.quizapp.question.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);
	
	
	
	@Query(value = "SELECT * FROM quizapp q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	
	List<Question> findRandomQuestinByCategory(String category, int numQ); 
}



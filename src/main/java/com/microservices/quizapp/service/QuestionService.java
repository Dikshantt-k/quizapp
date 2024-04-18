package com.microservices.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quizapp.question.Question;
import com.microservices.quizapp.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public ResponseEntity< List<Question>> getAllQuestion(){
		try {
		return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}

	public List<Question> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		return questionRepository.findByCategory(category);
	}

	public String addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionRepository.save(question);
		return "success";
	}
	
	

}

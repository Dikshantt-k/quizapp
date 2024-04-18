package com.microservices.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.quizapp.question.Question;
import com.microservices.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("allquestion")
	public ResponseEntity< List<Question>> getallquestin() {
		return questionService.getAllQuestion() ;
	}
	
	@GetMapping("category/{category}")
	public List<Question> getQuestionBycategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@PostMapping("add")
	public String addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
}

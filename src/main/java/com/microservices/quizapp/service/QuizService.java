package com.microservices.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quizapp.question.Question;
import com.microservices.quizapp.question.QuestionWrapper;
import com.microservices.quizapp.question.Quiz;
import com.microservices.quizapp.question.Response;
import com.microservices.quizapp.repository.QuestionRepository;
import com.microservices.quizapp.repository.QuizRepository;
@Service
public class QuizService {
	@Autowired
	 QuizRepository quizRepository;
	@Autowired
	 QuestionRepository questionRepository;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> question = questionRepository.findRandomQuestinByCategory(category , numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(question);
		quizRepository.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Question> questionFromDb = quiz.get().getQuestion();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		
		for(Question q : questionFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		Quiz quiz = quizRepository.findById(id).get();
		List<Question> questions = quiz.getQuestion();
		int right =0;
		int i =0;
		for(Response response1 : response) {
			if(response1.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
				i++;
		}
		
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}

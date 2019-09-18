package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.sevice.CategoryService;
import com.soprasteria.devopsassesmenttool.sevice.QuestionService;

@RestController
@RequestMapping("/devops")
public class QuestionController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/getAllQuestions", method = RequestMethod.GET)
	public List<Question> getQuestions(@RequestParam(defaultValue="DEVOPS") String type) {
		return questionService.getAllQuestions(type);
	}
	

	@RequestMapping(value = "/{categoryId}/question", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Question createQuestion(@PathVariable(value = "categoryId") Long categoryId,
			@RequestBody Question question) {
		return questionService.createQuestion(categoryId, question);
	}
	
	
	@RequestMapping(value = "/getQuestions/{categoryId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Set<Question> getQuestionByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
		return questionService.getQuestionsByCategoryId(categoryId);
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
	public Question getQuestionById(@PathVariable(value = "questionId") Long questionId) {
		return questionService.getQuestionById(questionId);
	}

	@RequestMapping(value = "/question", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Question updateQuestion( @RequestBody Question question) {
		return questionService.updateQuestionById(question);
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteQuestionById(@PathVariable(value = "questionId") Long questionId) {
		return questionService.deleteQuestionById(questionId);
	}

}

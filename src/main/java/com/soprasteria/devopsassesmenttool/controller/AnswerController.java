/**
 * 
 */
package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.sevice.AnswerService;
import com.soprasteria.devopsassesmenttool.sevice.UserService;

/**
 * @author dbkumar
 *
 */
@RestController
@RequestMapping("/devops")
public class AnswerController {

	@Autowired
	AnswerService answerService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/getAllAnswers", method = RequestMethod.GET)
	public List<Answer> getAnswers() {
		return answerService.getAllAnswers();
	}

	@RequestMapping(value = "user/{userId}/answer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Answer createAnswer(@PathVariable(value = "userId") Long userId, @RequestBody Answer answer) {
		return answerService.createAnswer(userId, answer);
	}

	@RequestMapping(value = "/answer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Answer updateAnswer(@RequestBody Answer answer) {
		return answerService.updateAnswerByAnswerId(answer);
	}

	@Transactional
	@RequestMapping(value = "/answer/{answerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAnswerByAnswerId(@PathVariable(value = "answerId") Integer answerId) {
		return answerService.deleteAnswerByAnswerId(answerId);
	}

	@RequestMapping(value = "/getAnswers/user/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Set<Answer> getAnswerByUserId(@PathVariable(value = "userId") Integer userId) {
		return answerService.getAnswerByUserId(userId);
	}

	@RequestMapping(value = "/answer/{answerId}", method = RequestMethod.GET)
	public Answer getAnswerById(@PathVariable(value = "answerId") Integer answerId) {
		return answerService.getAnswerByAnswerId(answerId);
	}

}
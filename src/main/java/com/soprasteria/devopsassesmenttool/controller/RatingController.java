/**
 * 
 */
package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;

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
import com.soprasteria.devopsassesmenttool.model.Rating;
import com.soprasteria.devopsassesmenttool.sevice.RatingService;

/**
 * @author dbkumar
 *
 */
@RestController
@RequestMapping("/devops")
public class RatingController {

	@Autowired
	RatingService ratingService;

	@RequestMapping(value = "/getAllRatings", method = RequestMethod.GET)
	public List<Rating> getRatings() {
		return ratingService.getAllRatings();
	}
	
	
	@RequestMapping(value = "question/{questionId}/rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Answer createAnswer(@PathVariable(value = "questionId") Integer questionId, @RequestBody Rating rating) {
		return ratingService.createAnswer(questionId, rating);
	}
	
	@Transactional
	@RequestMapping(value = "/question/rating/{ratingId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRatingByRatingId(@PathVariable(value = "ratingId") Integer ratingId) {
		return ratingService.deleteRatingByRatingId(ratingId);
	}

}

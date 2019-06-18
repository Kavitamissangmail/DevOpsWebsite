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

	@RequestMapping(value = "/getRating/{ratingId}", method = RequestMethod.GET)
	public Rating getRating(@PathVariable(value = "ratingId") Long ratingId) {
		return ratingService.getRatingByRatingId(ratingId);
	}

	@RequestMapping(value = "/question/{questionId}/getRatings", method = RequestMethod.GET)
	public Set<Rating> getRatingsByQuestionId(@PathVariable(value = "questionId") Long questionId) {
		return ratingService.getRatingByQuestionId(questionId);
	}

	@RequestMapping(value = "question/{questionId}/rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Rating createRating(@PathVariable(value = "questionId") Long questionId, @RequestBody Rating rating) {
		return ratingService.createRating(questionId, rating);
	}

	@RequestMapping(value = "rating", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Rating updateRating(@RequestBody Rating rating) {
		return ratingService.updateRating(rating);
	}

	@Transactional
	@RequestMapping(value = "/question/rating/{ratingId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRatingByRatingId(@PathVariable(value = "ratingId") Long ratingId) {
		return ratingService.deleteRatingByRatingId(ratingId);
	}

}

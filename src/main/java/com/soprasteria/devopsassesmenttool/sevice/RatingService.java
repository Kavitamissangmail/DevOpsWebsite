/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.Rating;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
import com.soprasteria.devopsassesmenttool.repository.RatingRepository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class RatingService {

	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	QuestionRepository questionRepository;

	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}
	
	public Rating getRatingByRatingId(Long ratingId) {
		return ratingRepository.findOne(ratingId);
	}
	
	public Set<Rating> getRatingByQuestionId(Long questionId) {
		Question question = questionRepository.findByQId(questionId);
		if (question == null) {
			throw new ResourceNotFoundException("Question with id " + questionId + " does not exist");
		}
		return ratingRepository.getRatingsByQuestionQId(questionId);
	}

	public ResponseEntity<Object> deleteRatingByRatingId(Long ratingId) {

		if (!ratingRepository.existsByRid(ratingId)) {
			throw new ResourceNotFoundException("Rating with id " + ratingId + " is not found");
		}

		ratingRepository.deleteByRid(ratingId);

		return ResponseEntity.ok().build();
	}

	public Rating createRating(Long questionId, Rating rating) {
		Question question = questionRepository.findByQId(questionId);
		if (question == null) {
			throw new ResourceNotFoundException("Question with id " + questionId + " does not exist");
		}

		// tie Question to Rating
		rating.setQuestion(question);

		Rating rating2 = ratingRepository.save(rating);
		return rating2;
	}
	
	public Rating updateRating(Rating ratingRequest) {
		if (!ratingRepository.existsByRid(ratingRequest.getRid())) {
			throw new ResourceNotFoundException("answer with id " + ratingRequest.getRid() + " not found");
		}
		Rating rating = ratingRepository.findByRid(ratingRequest.getRid());

		rating.setRatingValue(ratingRequest.getRatingValue());
		rating.setRatinglabel(ratingRequest.getRatinglabel());
		rating.setRatingDesc(ratingRequest.getRatingDesc());

		return ratingRepository.save(rating);
	}

}

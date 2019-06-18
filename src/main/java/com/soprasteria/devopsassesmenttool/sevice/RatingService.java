/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Answer;
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
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	public ResponseEntity<Object> deleteRatingByRatingId(Integer ratingId) {

		if (!ratingRepository.existsByRid(ratingId)) {
			throw new ResourceNotFoundException("answer with id " + ratingId + " not found");
		}

		ratingRepository.deleteByRid(ratingId);

		return ResponseEntity.ok().build();
	}

	public Answer createAnswer(Integer questionId, Rating rating) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*Set<Answer> answers = new HashSet<Answer>();

	Question question = questionRepository.findByQId(questionId);
	if (question == null) {
		throw new ResourceNotFoundException("question with id " + questionId + " does not exist");
	}

	rating.s
	// tie Category to Question
	answer.setUser(user);

	Answer answer2 = answerRepository.save(answer);
	// tie Question to Category
	answers.add(answer2);
	// category1.setQuestions(questions);

	return answer2;*/

}

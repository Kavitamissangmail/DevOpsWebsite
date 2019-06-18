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
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AnswerRepository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class AnswerService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AnswerRepository answerRepository;

	public List<Answer> getAllAnswers() {

		return answerRepository.findAll();
	}

	public Answer getAnswerByAnswerId(Integer answerId) {

		if (!answerRepository.existsByAnswerId(answerId)) {
			throw new ResourceNotFoundException("Answer with id " + answerId + " not found");
		}
		return answerRepository.findByAnswerId(answerId);
	}

	public Set<Answer> getQuestionsByCategoryId(Integer answerId) {
		return answerRepository.getAnswersByUserUserId(answerId);
	}

	public Answer createAnswer(Long userId, Answer answer) {
		Set<Answer> answers = new HashSet<Answer>();

		User user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User with id " + userId + " does not exist");
		}

		// tie Category to Question
		answer.setUser(user);

		Answer answer2 = answerRepository.save(answer);
		// tie Question to Category
		answers.add(answer2);
		// category1.setQuestions(questions);

		return answer2;
	}

	public Answer updateAnswerByAnswerId(Answer answerRequest) {
		if (!answerRepository.existsByAnswerId(answerRequest.getAnswerId())) {
			throw new ResourceNotFoundException("answer with id " + answerRequest.getAnswerId() + " not found");
		}
		Answer answer = answerRepository.findByAnswerId(answerRequest.getAnswerId());

		Answer answer1 = answer;

		answer1.setAnswerId(answerRequest.getAnswerId());
		answer1.setqId(answerRequest.getqId());
		answer1.setRatingId(answerRequest.getRatingId());
		answer1.setComment(answerRequest.getComment());

		return answerRepository.save(answer1);
	}

	public ResponseEntity<Object> deleteAnswerByAnswerId(Integer answerId) {
		if (!answerRepository.existsByAnswerId(answerId)) {
			throw new ResourceNotFoundException("answer with id " + answerId + " not found");
		}

		answerRepository.deleteByAnswerId(answerId);

		return ResponseEntity.ok().build();
	}

	public Set<Answer> getAnswerByUserId(Integer userId) {
		return answerRepository.getAnswersByUserUserId(userId);
	}

}

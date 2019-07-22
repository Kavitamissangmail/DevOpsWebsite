/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.Category;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AnswerRepository;
import com.soprasteria.devopsassesmenttool.repository.CategoryRepository;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.CategorizedAnswers;
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
	CategoryRepository catRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	QuestionRepository questionRepository;

	public List<Answer> getAllAnswers() {

		return answerRepository.findAll();
	}

	public Answer getAnswerByAnswerId(Long answerId) {

		if (!answerRepository.existsByAnswerId(answerId)) {
			throw new ResourceNotFoundException("Answer with id " + answerId + " not found");
		}
		return answerRepository.findByAnswerId(answerId);
	}

	public Set<Answer> getQuestionsByCategoryId(Long answerId) {
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
		Question question = questionRepository.findOne(answer.getqId());
		answer.setcId(question.getCategory().getcId());
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
		Question question = questionRepository.findOne(answerRequest.getqId());
		answer1.setAnswerId(answerRequest.getAnswerId());
		answer1.setqId(answerRequest.getqId());
		answer1.setRatingValue(answerRequest.getRatingValue());
		answer1.setComment(answerRequest.getComment());
		answer1.setTargetRatingValue(answerRequest.getTargetRatingValue());
		answer1.setTargetComment(answerRequest.getTargetComment());
		answer.setcId(question.getCategory().getcId());

		return answerRepository.save(answer1);
	}

	public ResponseEntity<Object> deleteAnswerByAnswerId(Long answerId) {
		if (!answerRepository.existsByAnswerId(answerId)) {
			throw new ResourceNotFoundException("answer with id " + answerId + " not found");
		}

		answerRepository.deleteByAnswerId(answerId);

		return ResponseEntity.ok().build();
	}

	public Set<Answer> getAnswerByUserId(Long userId) {
		return answerRepository.getAnswersByUserUserId(userId);
	}

	public List<CategorizedAnswers> getCategorizedAnswers(Long userId) {
		List<CategorizedAnswers> categorizedAnswers = new ArrayList<>();

		List<Category> categories = catRepository.findAll();

		if (!categories.isEmpty()) {
			categories.forEach(cat -> {
				CategorizedAnswers categorizedAnswer = new CategorizedAnswers();
				categorizedAnswer.setcId(cat.getcId());
				categorizedAnswer.setcName(cat.getCategoryName());
				categorizedAnswer.setAnswers(answerRepository.getAnswersByUserUserIdAndCId(userId, cat.getcId()));
				categorizedAnswers.add(categorizedAnswer);
			});

		}

		return categorizedAnswers;

	}

	public List<CategorizedAnswers> getCategorizedAnswersByCId(Long userId, Long CId) {
		List<CategorizedAnswers> categorizedAnswers = new ArrayList<>();

		Category c = catRepository.findOne(CId);

		CategorizedAnswers categorizedAnswer = new CategorizedAnswers();
		categorizedAnswer.setcId(c.getcId());
		categorizedAnswer.setcName(c.getCategoryName());
		categorizedAnswer.setAnswers(answerRepository.getAnswersByUserUserIdAndCId(userId, c.getcId()));
		categorizedAnswers.add(categorizedAnswer);

		return categorizedAnswers;

	}

}

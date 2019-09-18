/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Category;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.repository.CategoryRepository;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class QuestionService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	QuestionRepository questionRepository;

	public List<Question> getAllQuestions(String type) {
			return questionRepository.getQuestionsByAssessmentType(type);
	}

	public Question getQuestionById(Long questionId) {
		if (!questionRepository.existsByQId(questionId)) {
			throw new ResourceNotFoundException("Question with id " + questionId + " not found");
		}
		return questionRepository.findByQId(questionId);
	}

	public Question createQuestion(Long categoryId, Question question) {
		Set<Question> questions = new HashSet<Question>();

		Category category = categoryRepository.findByCId(categoryId);
		if (category != null) {
			throw new ResourceNotFoundException("category with id " + categoryId + " does not exist");
		}

		// tie Category to Question
		question.setCategory(category);

		Question question1 = questionRepository.save(question);
		// tie Question to Category
		questions.add(question1);
		// category1.setQuestions(questions);

		return question1;

	}

	public Question updateQuestionById(Question questionRequest) {
		if (!questionRepository.existsByQId(questionRequest.getqId())) {
			throw new ResourceNotFoundException("question with id " + questionRequest.getqId() + " not found");
		}
		Question question = questionRepository.findByQId(questionRequest.getqId());

		if (question == null) {
			throw new ResourceNotFoundException("question with id " + questionRequest.getqId() + " not found");
		}

		Question question1 = question;
		question1.setQuestionlabel(questionRequest.getQuestionlabel());
		question1.setQuestionDesc(questionRequest.getQuestionDesc());
		question1.setqId(questionRequest.getqId());
		question1.setAssessmentType(questionRequest.getAssessmentType());

		return questionRepository.save(question1);
	}

	@Transactional
	public ResponseEntity<Object> deleteQuestionById(Long questionId) {
		if (!questionRepository.existsByQId(questionId)) {
			throw new ResourceNotFoundException("question with id " + questionId + " not found");
		}

		questionRepository.deleteByQId(questionId);

		return ResponseEntity.ok().build();

	}

	public Set<Question> getQuestionsByCategoryId(Long categoryId) {
		return questionRepository.getQuestionsByCategoryCId(categoryId);
	}

}

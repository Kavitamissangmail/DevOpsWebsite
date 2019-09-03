package com.soprasteria.devopsassesmenttool.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	boolean existsByQId(Long questionId);

	Question findByQId(Long questionId);

	void deleteByQId(Long questionId);

	Set<Question> getQuestionsByCategoryCId(Long categoryId);
	
	List<Question> getQuestionsByAssessmentType(String type);
	
	 long countByAssessmentType(String type);
	

	


}
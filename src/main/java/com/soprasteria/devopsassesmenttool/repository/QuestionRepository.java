package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	boolean existsByQId(Integer questionId);

	Question findByQId(Integer questionId);

	void deleteByQId(Integer questionId);
	
	Set<Question> getQuestionsByCategoryCId(Integer categoryId);

}
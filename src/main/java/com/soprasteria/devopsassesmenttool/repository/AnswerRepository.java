/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Answer;

/**
 * @author dbkumar
 *
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

	boolean existsByAnswerId(Long answerId);

	Answer findByAnswerId(Long answerId);

	void deleteByAnswerId(Long answerId);

	Set<Answer> getAnswersByUserUserId(Long userId);

	Answer getAnswerByUserUserIdAndQId(Long userId, Long qId);
	
	Answer getAnswerByUserUserIdAndQIdAndAssessmentType(Long userId, Long qId,String type);

	List<Answer> getAnswersByUserUserIdAndCId(Long userId, Long cId);
	
	List<Answer> getAnswersByUserUserIdAndCIdAndAssessmentType(Long userId, Long cId,String type);
	
	 long countByUserUserId(Long userId);
	 
	 Set<Answer> getAnswersByUserUserIdAndAssessmentType(Long userId, String type);
	 
	 long countByUserUserIdAndAssessmentType(Long userId,String Agile);

}

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

	List<Answer> getAnswersByUserUserIdAndCId(Long userId, Long cId);
	
	 long countByUserUserId(Long userId);

}

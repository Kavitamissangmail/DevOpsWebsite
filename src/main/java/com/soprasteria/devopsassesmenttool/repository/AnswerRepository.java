/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Answer;

/**
 * @author dbkumar
 *
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	boolean existsByAnswerId(Integer answerId);

	Answer findByAnswerId(Integer answerId);

	void deleteByAnswerId(Integer answerId);

	Set<Answer> getAnswersByUserUserId(Integer userId);

}

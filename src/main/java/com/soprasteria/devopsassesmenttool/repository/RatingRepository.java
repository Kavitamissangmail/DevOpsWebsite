/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.Rating;

/**
 * @author dbkumar
 *
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	
	boolean existsByRid(Integer rid);

	Rating findByRid(Integer rid);

	void deleteByRid(Integer rid);
	
	Set<Rating> getRatingsByQuestionQId(Integer qId);


}



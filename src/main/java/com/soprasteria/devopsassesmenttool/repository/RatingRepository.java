/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Rating;

/**
 * @author dbkumar
 *
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	boolean existsByRid(Long rid);

	Rating findByRid(Long rid);

	void deleteByRid(Long rid);

	Set<Rating> getRatingsByQuestionQId(Long qId);
	
	Rating getRatingsByQuestionQIdAndRatingValue(Long qId,Long rvalue);

	


}

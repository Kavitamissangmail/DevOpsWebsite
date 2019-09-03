package com.soprasteria.devopsassesmenttool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByCId(Long id);

    Category findByCId(Long id);

	void deleteByCId(Long id);
	
	List<Category> getCategoryByAssessmentType(String type);

}
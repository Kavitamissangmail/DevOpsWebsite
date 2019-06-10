package com.soprasteria.devopsassesmenttool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	boolean existsByCId(Integer id);

	Optional<Category> findByCId(Integer id);

	void deleteByCId(Integer id);

}
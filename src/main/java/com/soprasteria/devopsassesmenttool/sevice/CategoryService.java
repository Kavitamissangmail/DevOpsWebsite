package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Category;
import com.soprasteria.devopsassesmenttool.repository.CategoryRepository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public Optional<Category> getCategoryById(Long categoryId) {
		if (!categoryRepository.existsByCId(categoryId)) {
			throw new ResourceNotFoundException("Author with id " + categoryId + " not found");
		}
		return categoryRepository.findByCId(categoryId);

	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);

	}

	public Category updateCategoryById(Long categoryId, Category categoryRequest) {
		if (!categoryRepository.existsByCId(categoryId)) {
			throw new ResourceNotFoundException("category with id " + categoryId + " not found");
		}
		Optional<Category> category = categoryRepository.findByCId(categoryId);

		if (!category.isPresent()) {
			throw new ResourceNotFoundException("category with id " + categoryId + " not found");
		}

		Category category1 = category.get();
		category1.setcId(categoryRequest.getcId());
		category1.setCategoryName(categoryRequest.getCategoryName());
		return categoryRepository.save(category1);

	}

	public ResponseEntity<Object> deleteCategoryById(Long categoryId) {
		if (!categoryRepository.existsByCId(categoryId)) {
			throw new ResourceNotFoundException("category with id " + categoryId + " not found");
		}

		categoryRepository.deleteByCId(categoryId);

		return ResponseEntity.ok().build();

	} 

}

package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.Category;
import com.soprasteria.devopsassesmenttool.sevice.CategoryService;
import com.soprasteria.devopsassesmenttool.sevice.QuestionService;
import com.soprasteria.devopsassesmenttool.util.CustomErrorType;

@RestController
@RequestMapping("/devops")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	public List<Category> getCategories() {
		return categoryService.getCategories();
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
	public Optional<Category> getCategoryById(@PathVariable(value = "categoryId") Integer categoryId) {
		return categoryService.getCategoryById(categoryId);
	}

	@RequestMapping(value = "/category", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Category updateCategory(@PathVariable(value = "categoryId") Integer categoryId,
			@RequestBody Category category) {
		return categoryService.updateCategoryById(categoryId, category);
	}

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCategoryById(@PathVariable(value = "categoryId") Integer categoryId) {
		return categoryService.deleteCategoryById(categoryId);
	}

}

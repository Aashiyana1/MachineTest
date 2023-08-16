package com.productcatalog.catalog.category.controller;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.catalog.category.model.Category;
import com.productcatalog.catalog.category.service.CategoryService;
import com.productcatalog.exception.CustomException;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * @return List of all categories
	 */
	@GetMapping(produces = "application/json")
	public List<Category> getListOfAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping(path = "/{categoryId}", produces = "application/json")
	public Category getCategoryById(@PathVariable Long categoryId) {
		try {
			return categoryService.getCategoryById(categoryId);
		} catch (CustomException e) {
			return null;
		}
	}

	/**
	 * @param category
	 * @return On success, Status = CREATED
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Status addCategories(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	/**
	 * @param categoryId
	 * @return On success, Status = NO_CONTENT
	 * @return On id not found, Status = NOT_FOUND
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@DeleteMapping(path = "/{categoryId}", produces = "application/json")
	public Status deleteCategory(@PathVariable Long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}

	/**
	 * @param categoryId,
	 *            category
	 * @return On success, Status = NO_CONTENT
	 * @return On id not found, Status = NOT_FOUND
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@PutMapping(path = "/{categoryId}", produces = "application/json")
	public Status updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		return categoryService.updateCategory(categoryId, category);
	}

	// getter setter
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setProductService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}

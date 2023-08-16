package com.productcatalog.catalog.category.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcatalog.catalog.category.dao.CategoryDao;
import com.productcatalog.catalog.category.dao.CategoryRepository;
import com.productcatalog.catalog.category.model.Category;
import com.productcatalog.catalog.category.service.CategoryService;
import com.productcatalog.exception.CustomException;
import com.productcatalog.exception.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryDao categoryDao;

	/**
	 * @return List of all categories
	 */
	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * @param category
	 * @return HTTP response depending upon result
	 */
	@Override
	public Status addCategory(Category category) {
		if (null != category) {
			categoryRepository.save(category);
			return Response.Status.CREATED;
		} else {
			return Response.Status.BAD_REQUEST;
		}
	}

	/**
	 * Performs delete operation on categories by user authentication
	 */
	@Override
	public Status deleteCategory(Long categoryId) {
		if (null != categoryId) {
			try {
				categoryRepository.deleteById(categoryId);
				return Response.Status.NO_CONTENT;
			} catch (IllegalArgumentException e) {
				return Response.Status.NOT_FOUND;
			}
		} else {
			return Response.Status.BAD_REQUEST;
		}
	}

	/**
	 * returns category details by provided category id
	 */
	@Override
	public Category getCategoryById(Long categoryId) throws CustomException {
		Optional<Category> category = null;
		if (null != categoryId) {
			category = categoryRepository.findById(categoryId);
			if (category.isPresent())
				return category.get();
		} else {
			throw new ResourceNotFoundException("Requested record not found!");
		}
		return null;
	}

	/**
	 * returns status upon result of update operation
	 */
	@Override
	public Status updateCategory(Long categoryId, Category category) {
		if (null != categoryId && null != category) {
			categoryDao.updateCategoryById(categoryId, category);
			return Response.Status.OK;
		} else {
			return Response.Status.BAD_REQUEST;
		}
	}

	// getter setter
	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}

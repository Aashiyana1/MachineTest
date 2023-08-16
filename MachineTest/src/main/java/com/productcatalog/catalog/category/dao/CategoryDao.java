package com.productcatalog.catalog.category.dao;

import org.springframework.stereotype.Repository;

import com.productcatalog.catalog.category.model.Category;

@Repository
public interface CategoryDao {

	Long findCategoryIdByCategory(String categoryToFind);

	void updateCategoryById(Long categoryId, Category category);
}

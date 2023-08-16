package com.productcatalog.catalog.category.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.productcatalog.catalog.category.dao.CategoryDao;
import com.productcatalog.catalog.category.dao.CategoryRepository;
import com.productcatalog.catalog.category.model.Category;
import com.productcatalog.exception.CustomException;
import com.productcatalog.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCategoryServiceImpl {

	private CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryDao categoryDao;

	private Category category = new Category();
	private Optional<Category> optionalCategory = Optional.of(category);
	private List<Category> categoryList = new ArrayList<>();

	@Before
	public void setup() {
		categoryServiceImpl.setCategoryRepository(categoryRepository);
		categoryServiceImpl.setCategoryDao(categoryDao);
	}

	private void setEntities() {
		category.setCategoryId(1L);
		category.setCategoryName("categoryName");
		category.setDescription("categoryDescription");
		categoryList.add(category);
	}

	/**
	 * Get all categories
	 */
	@Test
	public void testGetAllCategories() {
		setEntities();
		Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
		List<Category> list = categoryServiceImpl.getAllCategories();
		assertNotNull(list);
		assertEquals("categoryName", list.get(0).getCategoryName());
		assertEquals("categoryDescription", list.get(0).getDescription());
		assertEquals(Long.valueOf(1L), list.get(0).getCategoryId());
	}

	/**
	 * Add category
	 */
	@Test
	public void testAddCategory() {
		setEntities();
		assertEquals(Response.Status.CREATED, categoryServiceImpl.addCategory(category));
	}

	/**
	 * Add category when category is null
	 */
	@Test
	public void testAddCategoryWhenCategoryIsNull() {
		setEntities();
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.addCategory(null));
	}

	/**
	 * Delete category when category id is null
	 */
	@Test
	public void testDeleteCategoryWhenCategoryIdIsNull() {
		setEntities();
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.deleteCategory(null));
	}

	/**
	 * Delete category when category id is valid
	 */
	@Test
	public void testDeleteCategoryWhenCategoryIdIsValid() {
		setEntities();
		assertEquals(Response.Status.NO_CONTENT, categoryServiceImpl.deleteCategory(1L));
	}

	/**
	 * Delete category when category id is valid
	 */
	@Test
	public void testDeleteCategoryWhenCategoryIdIsNotValid() {
		setEntities();
		Mockito.doThrow(IllegalArgumentException.class).when(categoryRepository).deleteById(1L);
		assertEquals(Response.Status.NOT_FOUND, categoryServiceImpl.deleteCategory(1L));
	}

	/**
	 * Get category by id when categoryId is null
	 */
	@Test
	public void testGetCategoryByIdWhenIdIsNull() {
		setEntities();
		try {
			categoryServiceImpl.getCategoryById(null);
		} catch (CustomException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * Get category by id when categoryId
	 */
	@Test
	public void testGetCategoryById() {
		setEntities();
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		try {
			Category category = categoryServiceImpl.getCategoryById(1L);
			assertNotNull(category);
			assertEquals("categoryName", category.getCategoryName());
			assertEquals("categoryDescription", category.getDescription());
			assertEquals(Long.valueOf(1L), category.getCategoryId());
		} catch (CustomException e) {
		}
	}

	/**
	 * Get category by id when categoryId
	 */
	@Test
	public void testGetCategoryByIdWhenOptionalCategoryIsBlank() {
		setEntities();
		Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
		try {
			assertNull(categoryServiceImpl.getCategoryById(1L));
		} catch (CustomException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * update category when category and category id are valid
	 */
	@Test
	public void testUpdateCategory() {
		setEntities();
		Mockito.doNothing().when(categoryDao).updateCategoryById(1L, category);
		assertEquals(Response.Status.OK, categoryServiceImpl.updateCategory(1L, category));
	}

	/**
	 * update category when category id is null
	 */
	@Test
	public void testUpdateCategoryWhenCategoryIdIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.updateCategory(null, category));
	}

	/**
	 * update category when category is null
	 */
	@Test
	public void testUpdateCategoryWhenCategoryIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.updateCategory(1L, null));
	}

	/**
	 * update category when category and category id are null
	 */
	@Test
	public void testUpdateCategoryWhenCategoryAndCategoryIdAreNull() {
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.updateCategory(null, null));
	}
}

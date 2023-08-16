package com.productcatalog.catalog.product.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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
import com.productcatalog.catalog.product.dao.ProductDao;
import com.productcatalog.catalog.product.dao.ProductRepository;
import com.productcatalog.catalog.product.model.Product;
import com.productcatalog.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductServiceImpl {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CategoryDao categoryDao;

	@Mock
	private ProductDao productDao;

	@Mock
	private CategoryRepository categoryRepository;

	private ProductServiceImpl productServiceImpl = new ProductServiceImpl();

	private List<Product> productList = new ArrayList<>();
	private Category category = new Category();
	private Optional<Category> optionalCategory = Optional.of(category);
	private Product product = new Product();
	private Optional<Product> optionalProduct = Optional.of(product);

	@Before
	public void setup() {
		productServiceImpl.setProductRepository(productRepository);
		productServiceImpl.setProductDao(productDao);
		productServiceImpl.setCategoryRepository(categoryRepository);
		productServiceImpl.setCategoryDao(categoryDao);
	}

	private void setEntities() {
		product.setCategoryId(1L);
		product.setCategory("categoryName");
		product.setDescription("categoryDescription");
		product.setProductCost(BigDecimal.valueOf(1000L));
		product.setProductName("productName");
		productList.add(product);

		category.setCategoryId(1L);
		category.setCategoryName("categoryName");
		category.setDescription("categoryDescription");
	}

	/**
	 * Get list of all products
	 */
	@Test
	public void testGetListOfAllProducts() {
		setEntities();
		Mockito.when(productRepository.findAll()).thenReturn(productList);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		List<Product> list = productServiceImpl.getListOfAllProducts();
		assertNotNull(list);
		assertEquals("categoryName", list.get(0).getCategory());
		assertEquals(Long.valueOf(1L), list.get(0).getCategoryId());
		assertEquals("categoryDescription", list.get(0).getDescription());
		assertEquals(BigDecimal.valueOf(1000L), list.get(0).getProductCost());
		assertEquals("productName", list.get(0).getProductName());
	}

	/**
	 * Get product by id
	 */
	@Test
	public void testGetProductById() {
		setEntities();
		Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		try {
			assertNotNull(productServiceImpl.getProductById(1L));
		} catch (ResourceNotFoundException e) {

		}
	}

	/**
	 * when productId is null
	 */
	@Test
	public void testGetProductByIdWhenProductIdIsNull() {
		setEntities();
		Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		try {
			productServiceImpl.getProductById(null);
		} catch (ResourceNotFoundException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * Throws ResourceNotFoundException
	 */
	@Test
	public void testGetProductByIdWhenResourceNotFoundExceptionIsThrown() {
		Optional<Product> product = Optional.empty();
		Mockito.when(productRepository.findById(1L)).thenReturn(product);
		try {
			productServiceImpl.getProductById(1L);
		} catch (ResourceNotFoundException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * Throws ResourceNotFoundException
	 */
	@Test
	public void testGetProductByIdWhenOptionalCategoryIsBlank() {
		Optional<Category> category = Optional.empty();
		Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(category);
		try {
			Product product = productServiceImpl.getProductById(1L);
			assertNotNull(product);
			assertEquals(null, product.getCategory());
		} catch (ResourceNotFoundException e) {
		}
	}

	/**
	 * Delete product
	 */
	@Test
	public void testDeleteProduct() {
		Mockito.doNothing().when(productRepository).deleteById(1L);
		assertEquals(Response.Status.NO_CONTENT, productServiceImpl.deleteProduct(1L));
	}

	/**
	 * Delete product when product id is null
	 */
	@Test
	public void testDeleteProductWhenProductIdIsNull() {
		Mockito.doNothing().when(productRepository).deleteById(null);
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.deleteProduct(null));
	}

	/**
	 * When Product is valid
	 */
	@Test
	public void testAddProduct() {
		setEntities();
		assertEquals(Response.Status.CREATED, productServiceImpl.addProduct(product));
	}

	/**
	 * When product is null
	 */
	@Test
	public void testAddProductWhenProductIsNull() {
		setEntities();
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.addProduct(null));
	}

	/**
	 * update the product
	 */
	@Test
	public void testUpdateProduct() {
		Mockito.doNothing().when(productDao).updateProduct(1L, product);
		assertEquals(Response.Status.OK, productServiceImpl.updateProduct(1L, product));
	}

	/**
	 * update the product When Product Id Is Null
	 */
	@Test
	public void testUpdateProductWhenProductIdIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.updateProduct(null, product));
	}

	/**
	 * update the product When Product Is Null
	 */
	@Test
	public void testUpdateProductWhenProductIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.updateProduct(1L, null));
	}

	/**
	 * update the product When Product and productId Is Null
	 */
	@Test
	public void testUpdateProductWhenProductAndProductIdIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.updateProduct(null, null));
	}

	/**
	 * Get Product List by category Id
	 */
	@Test
	public void testGetProductListByCategoryId() {
		setEntities();
		Mockito.when(productDao.getproductByCategoryId(1L)).thenReturn(productList);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		List<Product> list = productServiceImpl.getProductListByCategoryId(1L);
		assertNotNull(list);
		assertEquals("categoryName", list.get(0).getCategory());
	}

	/**
	 * Get Product List when category Id is null
	 */
	@Test
	public void testGetProductListByCategoryIdWhenCategoryIdIsNull() {
		setEntities();
		Mockito.when(productDao.getproductByCategoryId(1L)).thenReturn(productList);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		List<Product> list = productServiceImpl.getProductListByCategoryId(null);
		assertNotNull(list);
		assertTrue(list.isEmpty());
	}
}

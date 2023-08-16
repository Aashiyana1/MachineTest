package com.productcatalog.catalog.product.controller;

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

import com.productcatalog.catalog.product.model.Product;
import com.productcatalog.catalog.product.service.ProductService;
import com.productcatalog.exception.ResourceNotFoundException;

/**
 * Product controller
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * @return list of all products
	 */
	@GetMapping(produces = "application/json")
	public List<Product> getListOfAllProducts() {
		return productService.getListOfAllProducts();
	}

	/**
	 * @param productId
	 * @return returns product that belong to the provided productId
	 */
	@GetMapping(path = "/{productId}", produces = "application/json")
	public Product getProductById(@PathVariable Long productId) {
		try {
			return productService.getProductById(productId);
		} catch (ResourceNotFoundException e) {
			return null;
		}
	}

	/**
	 * @param productId
	 * @return On success, Status = NO_CONTENT
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On resource not found, Status = NOT_FOUND
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@DeleteMapping(path = "/{productId}", produces = "application/json")
	public Status deleteProducts(@PathVariable Long productId) {
		return productService.deleteProduct(productId);
	}

	/**
	 * @param product
	 * @return On success, Status = CREATED
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On resource not found, Status = NOT_FOUND
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Status addProducts(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	/**
	 * @param productId
	 * @return On success, Status = OK
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@PutMapping(path = "/{productId}", consumes = "application/json", produces = "application/json")
	public Status updateProducts(@PathVariable Long productId, @RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}

	/**
	 * @param categoryId
	 * @return List of Products
	 */
	@GetMapping(path = "/categories/{categoryId}", consumes = "application/json", produces = "application/json")
	public List<Product> getProductsByCategoryId(@PathVariable Long categoryId) {
		return productService.getProductListByCategoryId(categoryId);
	}

	// getter setter
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}

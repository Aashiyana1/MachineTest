package com.productcatalog.catalog.product.dao;

import java.util.List;

import javax.inject.Named;

import com.productcatalog.catalog.product.model.Product;

@Named("productDaoBean")
public interface ProductDao {

	void updateProduct(Long productId, Product product);

	List<Product> getproductByCategoryId(Long categoryId);

}

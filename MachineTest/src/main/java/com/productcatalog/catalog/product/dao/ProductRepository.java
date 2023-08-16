package com.productcatalog.catalog.product.dao;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcatalog.catalog.product.model.Product;

@Repository
@Named("productRepositoryBean")
public interface ProductRepository extends JpaRepository<Product, Long> {

}

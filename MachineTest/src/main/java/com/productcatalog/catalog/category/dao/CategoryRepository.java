package com.productcatalog.catalog.category.dao;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcatalog.catalog.category.model.Category;

@Repository
@Named("categoryRespositoryBean")
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

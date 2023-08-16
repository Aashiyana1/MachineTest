package com.productcatalog.catalog.product.dao.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.productcatalog.catalog.product.dao.ProductDao;
import com.productcatalog.catalog.product.model.Product;
import com.productcatalog.database.connectionconfig.DatabaseConnectionConfig;

@Named("productDaoImplBean")
public class ProductDaoImpl implements ProductDao {

	private static Logger logger = Logger.getLogger(ProductDaoImpl.class);

	/**
	 * @param productId
	 *            Product updates product details using provided product id
	 */
	@Override
	public void updateProduct(Long productId, Product product) {
		try {
			EntityManager entityManager = DatabaseConnectionConfig.getEntityManager();
			entityManager.getTransaction().begin();
			String updateSqlToExecute = "UPDATE Product p SET" + " p.productName = " + "'" + product.getProductName()
					+ "'," + " p.productCost = " + "'" + product.getProductCost() + "'," + " p.categoryId = " + "'"
					+ product.getCategoryId() + "'," + " p.description = " + "'" + product.getDescription() + "'"
					+ " WHERE p.id = " + "'" + productId + "'";
			Query query = entityManager.createQuery(updateSqlToExecute);
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Got exception while executing update Product method");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getproductByCategoryId(Long categoryId) {
		String selectStringToExecute = "SELECT p from Product p WHERE p.categoryId = " + "'" + categoryId + "'";
		Query query = DatabaseConnectionConfig.getEntityManager().createQuery(selectStringToExecute);
		return query.getResultList();
	}
}

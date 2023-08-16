package com.productcatalog.catalog.category.dao.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.productcatalog.catalog.category.dao.CategoryDao;
import com.productcatalog.catalog.category.model.Category;
import com.productcatalog.database.connectionconfig.DatabaseConnectionConfig;

@Named("categoryDaoBean")
public class CategoryDaoImpl implements CategoryDao {

	private static Logger logger = Logger.getLogger(CategoryDaoImpl.class);

	/**
	 * @param category
	 * @return categoryId
	 */
	public Long findCategoryIdByCategory(String categoryToFind) {
		logger.info("Executing sql to get category id from provided category");
		String sqlToExecute = "select category.categoryId from Category category where category.categoryName = " + "'"
				+ categoryToFind + "'";
		Query query = DatabaseConnectionConfig.getEntityManager().createQuery(sqlToExecute);
		@SuppressWarnings("unchecked")
		List<Long> idList = query.getResultList();
		return idList.get(0);
	}

	/**
	 * @param categoryId,
	 *            category
	 */
	public void updateCategoryById(Long categoryId, Category category) {
		logger.info("Executing sql to update category by provided category id");
		try {
			EntityManager entityManager = DatabaseConnectionConfig.getEntityManager();
			entityManager.getTransaction().begin();
			String sqlToExecute = "UPDATE Category c SET c.categoryName =" + "'" + category.getCategoryName() + "', "
					+ "c.description = " + "'" + category.getDescription() + "' " + "WHERE c.categoryId = " + "'"
					+ categoryId + "'";
			Query query = entityManager.createQuery(sqlToExecute);
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Got exception while executing update query");
		}
	}

}

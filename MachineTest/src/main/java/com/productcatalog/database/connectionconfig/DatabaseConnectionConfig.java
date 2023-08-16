package com.productcatalog.database.connectionconfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class DatabaseConnectionConfig {
	/**
	 * private constructor
	 */
	private DatabaseConnectionConfig() {
	}

	private static Logger logger = Logger.getLogger(DatabaseConnectionConfig.class);

	private static final String JPA_STRING = "local_JPA";

	public static EntityManager getEntityManager() {
		logger.info("Creating entity manager factory..");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(JPA_STRING);
		return emfactory.createEntityManager();
	}
}

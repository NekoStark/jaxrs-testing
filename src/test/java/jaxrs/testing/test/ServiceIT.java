package jaxrs.testing.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import io.restassured.RestAssured;

public abstract class ServiceIT {
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("it");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		initTestData();
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.getTransaction().begin();

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/jaxrs-testing/rest/1.0";
	}

	@After
	public void tearDown() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
	}

	protected abstract void initTestData() throws Exception;
}

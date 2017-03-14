package jaxrs.testing.test;

import java.util.Set;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.After;
import org.junit.Before;

import io.restassured.RestAssured;
import jaxrs.testing.service.utils.NotFoundMapper;

public abstract class ServiceUnitTest {

	private static final Integer PORT = 12345;
	protected TJWSEmbeddedJaxrsServer server;

	@Before
	public void setUp() throws Exception {
		initTestData();

		server = new TJWSEmbeddedJaxrsServer();
		server.setPort(PORT);
		RestAssured.port = PORT;
		for (Object endpoint : getEndpoints()) {
			server.getDeployment().getResources().add(endpoint);
		}
		server.getDeployment().getProviders().add(new NotFoundMapper());
		server.start();
	}

	@After
	public void tearDown() {
		server.stop();
	}

	protected abstract void initTestData() throws Exception;

	protected abstract Set<Object> getEndpoints() throws Exception;
}
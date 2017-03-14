package jaxrs.testing.test;

import org.junit.Test;

import jaxrs.testing.model.User;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class UserEndpointIT extends ServiceIT { 
	private User user;

	@Test
	public void testGet () { 
		get("/users/"+user.getId())
		.then()
		.assertThat()
		.body( "id", equalTo(Integer.valueOf(1)),
				"userName", equalTo("testuser"), 
				"email", equalTo("testuser@email.com"),
				"password", nullValue() );
	}

	@Test
	public void testGetWithError() { 
		get("/users/a").then().statusCode(400);
		get("/users/" + Long.valueOf(-1)).then().statusCode(400);
	}

	@Test
	public void testGetNotFound() { 
		get("/users/9999").then().statusCode(404);
	}
	@Override
	protected void initTestData() throws Exception { 
		user = new User(); 
		user.setUserName("testuser"); 
		user.setEmail("testuser@email.com"); 
		user.setPassword("mypass");
		entityManager.persist(user); 
	}
}
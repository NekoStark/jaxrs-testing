package jaxrs.testing.test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import jaxrs.testing.dao.UserDao;
import jaxrs.testing.model.User;
import jaxrs.testing.service.UserEndpoint;



public class UserEndpointTest extends ServiceUnitTest{
 
	
	private UserDao userDao;
	private User user;


	@Test
	public void testGet () { 
		when(userDao.find(user.getId())).thenReturn(user);
		get("/users/"+user.getId())
		.then()
		.assertThat()
		.body("id", equalTo(user.getId().intValue())) .and()
		.body("userName", equalTo(user.getUserName())) .and()
		.body("email", equalTo(user.getEmail())) .and()
		.body("password", nullValue());
	}

	@Test
	public void testGetWithError() { 
		get("/users/a")
		.then()
		.statusCode(400);

		get("/users/"+Long.valueOf(-1))
		.then()
		.statusCode(400);
		
	
	}
	
	
	@Test
	public void testGetNotFound() { 
		
		get("/users/9999")
		.then()
		.statusCode(404);
	}




	@Override
	protected Set<Object> getEndpoints() throws Exception {
		userDao = mock(UserDao.class);
		UserEndpoint userEndpoint = new UserEndpoint(); 
		FieldUtils.writeField(userEndpoint , "userDao", userDao , true); 
		return Collections.singleton(userEndpoint);
	}

	@Override
	protected void initTestData() {
		user = new User();
		user.setId(new Long(1));
		user.setEmail("test@unifi.it");
		user.setUserName("username");
	}
}

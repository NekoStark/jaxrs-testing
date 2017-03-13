package jaxrs.testing.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Response;

import jaxrs.testing.dao.UserDao;
import jaxrs.testing.model.User;


@Path ("/users")
public class UserEndpoint { 
	@Inject
	private UserDao userDao;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Long userId) {
		if(userId < 1) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		User result = userDao.find(userId);
		if(result == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response
				.status(Response.Status.OK) .entity(result)
				.build();
	}

}

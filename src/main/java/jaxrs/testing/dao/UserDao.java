package jaxrs.testing.dao;

import java.util.List;

import jaxrs.testing.model.User;

public interface UserDao {

	public List<User> query();

	public User find(Long id);

	public void save(User user);

}

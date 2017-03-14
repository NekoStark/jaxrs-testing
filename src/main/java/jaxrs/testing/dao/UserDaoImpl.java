package jaxrs.testing.dao;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jaxrs.testing.model.User;

@Default
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> query() {
		// TODO add implementation
		return null;
	}

	@Override
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void save(User user) {
		// TODO add implementation
	}

}

package jaxrs.testing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jaxrs.testing.model.User;

public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<User> query(){
		return entityManager
				.createQuery("from User u ", User.class)
				.getResultList();
	}
	public User find(Long id){
		return entityManager.find(User.class,id);
	}
	public void save(User user){
		entityManager.persist(user);
	}
}

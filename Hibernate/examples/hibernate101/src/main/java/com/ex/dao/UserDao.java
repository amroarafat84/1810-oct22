package com.ex.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ex.models.User;
import com.ex.util.ConnectionUtil;

public class UserDao {

	static ConnectionUtil util = ConnectionUtil.getInstance();

	/*
	 * SAVE
	 * - adds instance to db
	 * - persists transient instance, returns whatever Serializable
	 * identifier is used for the instance 
	 * - saving a persisted instance does nothing 
	 * - saving a detached instance creates a new persistant instance
	 * and assigns it a new identifier, which results in a duplicate
	 * record. do not do!
	 */
	public User save(User u) {
		Session session = util.getSession();
		try {
			Transaction tx = session.beginTransaction();
			int id = (int) session.save(u);
			tx.commit();
			u.setId(id);
		}
		finally {
			session.close();
		}
		return u;
	}
	
	//PERSIST
	
	//UPDATE
	public void update(User u) {
		Session session = util.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(u);
			tx.commit();
		}
		finally {
			session.close();
		}
	}
	
	//MERGE
	
	/*
	 * Session.get()
	 * - returns persisted object with specified identifier
	 * - if the object does not exist, the method will return null (unlike load())
	 * - method hits the database immediately(unlike load)
	 * - method to use to retrieve data that you are not sure exists
	 */
	public User getById(int id) {
		Session session = util.getSession();
		User u = (User) session.get(User.class, id);
		session.close();
		return u;
	}
	
	/*
	 * Session.load()
	 * - the laod method provides a way of retrieving a persistant 
	 * instance if you know its id. Load() take a class object and 
	 * loads the state into a new instantiated instance of that 
	 * class in a persistent state
	 * - Load() will throw an ObjectNotFoundException if it attempts
	 * to load a non existent row
	 * - the method returns a proxy of the object and does not hit the
	 * database until an object method is called (while session is still open). 
	 */
	public User loadById(int id) {
		Session session = util.getSession();
		User u = (User) session.load(User.class, id);
		System.out.println("LOADED USER");
		System.out.println("IN USER DAO. LOADED USER OF ID " + id);
		System.out.println(u.toString());
		session.close();
		return u;
	}
	
	/*
	 * QUERY API
	 * Query Database using HQL
	 */
	public User findByUsername(String username){
		User u = null;
		Session session = util.getSession();
		Query q = session.createQuery("from User where lower(username) = :param");
		q.setParameter("param", username.toLowerCase());
		u = (User) q.uniqueResult();
		return u;
	}
	
	public Set<User> findFollowers(User u){
		Set<User> followerList = new HashSet<User>();
		Session session = util.getSession();
		Query q = session.createQuery("from User where ------------- "); //select from bidirectional mapping
		return null;
	}
	
	/*
	 * CRITERIA
	 */
	public List<User> findAll(){
		List<User> users = null;
		Session session = util.getSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			users = criteria.list();
		}
		finally {
			session.close();
		}
		return users;
	}
	

}

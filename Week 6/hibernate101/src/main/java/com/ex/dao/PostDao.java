package com.ex.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ex.models.Post;
import com.ex.util.ConnectionUtil;

public class PostDao {
	
	static ConnectionUtil util = ConnectionUtil.getInstance();
	
	public Post save(Post p) {
		Session s = util.getSession();
		try {
			Transaction tx = s.beginTransaction();
			
			int pId = (int) s.save(p);
			p.setId(pId);
			tx.commit();
		} finally {
			s.close();
		}
		return p;
	}

}
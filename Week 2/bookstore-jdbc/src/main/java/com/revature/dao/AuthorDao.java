package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Author;
import com.revature.pojos.Genre;
import com.revature.util.ConnectionFactory;

public class AuthorDao implements DAO <Author,Integer> {

	@Override
	public List<Author> findAll() {
		
		List<Author> authors = new ArrayList<Author>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM BN_AUTHOR"; //no semicolon
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB Query
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Author temp = new Author();
				temp.setId(rs.getInt(1));
				temp.setFirstName(rs.getString(2));
				authors.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return authors;
	}

	@Override
	public Author findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author save(Author obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author update(Author obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Author obj) {
		// TODO Auto-generated method stub
		
	}

}
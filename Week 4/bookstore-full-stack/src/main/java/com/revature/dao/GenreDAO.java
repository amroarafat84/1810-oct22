package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Genre;
import com.revature.util.ConnectionFactory;

public class GenreDAO implements DAO<Genre, Integer>{
	
	/*
	 * STATEMENT - important interface in JDBC API
	 * -takes an SQL statement as a string, executes it,
	 * and returns the result
	 * -allow SQL injection. These are not ideal to use. Definitely
	 * do not use for any SQL command that uses a parameter/variable
	 * 
	 * SQL injection - common hacking technique. It is the insertion of
	 * code as input that affects you database
	 */

	@Override
	public List<Genre> findAll() {
		List<Genre> genres = new ArrayList<Genre>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM BN_GENRE ORDER BY name"; //no semicolon
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB Query
			ResultSet rs = statement.executeQuery(query);
			
			/*
			 * Moves the cursor forward one row from its current position.A ResultSet cursor is initially 
			 * positioned before the first row; the first call to the method next makes the first row the 
			 * current row; the second call makes the second row the current row, and so on. 
			 * When a call to the next method returns false,the cursor is positioned after the last row. 
			 * Any invocation of a ResultSet method which requires a current row will result in a SQLException 
			 * being thrown.If the result set type is TYPE_FORWARD_ONLY, it is vendor specified whether their 
			 * JDBC driver implementation will return false or throw an SQLException on a subsequent call to next. 
			 * If an input stream is open for the current row, a call to the method next will implicitly close it. 
			 * A ResultSet object'swarning chain is cleared when a new row is read.
			 */
			while (rs.next()) {
				Genre temp = new Genre();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				genres.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}

	
	/*
	 * Prepared Statement
	 */
	public Genre findByID(Integer id) {
		Genre g = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM BN_GENRE WHERE GENRE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				g = new Genre();
				g.setId(rs.getInt(1));
				g.setName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

	public Genre save(Genre obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO BN_GENRE(NAME) VALUES(?)";
			String[] keyNames = {"Genre_Id"};
			
			PreparedStatement ps = conn.prepareStatement(sql,keyNames);
			ps.setString(1, obj.getName());
			
			int numRows = ps.executeUpdate();
			if(numRows == 1) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setId(pk.getInt(1));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	public Genre update(Genre obj) {
		
		return null;
	}

	public void delete(Genre obj) {
		
		
	}
	
	

}

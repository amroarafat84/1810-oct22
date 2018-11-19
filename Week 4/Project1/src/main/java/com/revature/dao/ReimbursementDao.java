package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Reimbursement;
import com.revature.util.ConnectionFactory;

public class ReimbursementDao implements DAO<Reimbursement,Integer>{
	
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM ERS_REIMBURSEMENT ORDER BY REIMB_ID"; //no semicolon
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB Query
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setId(rs.getInt(1));
				temp.setAmount(rs.getDouble(2));
				temp.setSubmitted(rs.getDate(3));
				temp.setResolved(rs.getDate(4));
				temp.setDescription(rs.getString(5));
				temp.setAuthor(rs.getInt(6));
				temp.setResolver(rs.getInt(7));
				temp.setStatus(rs.getInt(8));
				temp.setType(rs.getInt(9));
				reimbs.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	
	/*
	 * Prepared Statement
	 */
	public Reimbursement findByID(Integer id) {
		Reimbursement r = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Reimbursement();
				r.setId(rs.getInt(1));
				r.setAmount(rs.getDouble(2));
				r.setSubmitted(rs.getDate(3));
				r.setResolved(rs.getDate(4));
				r.setDescription(rs.getString(5));
				r.setAuthor(rs.getInt(6));
				r.setResolver(rs.getInt(7));
				r.setStatus(rs.getInt(8));
				r.setType(rs.getInt(9));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public Reimbursement insert(Reimbursement obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_USER(AMOUNT,SUBMITTED,RESOLVED,DESCRIPTION,AUTHOR,RESOLVER"
					+ ",STATUS,TYPE) VALUES(?,?,?,?,?,?,?,?)";
			String[] keyNames = {"USER_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql,keyNames);
			ps.setDouble(1, obj.getAmount());
			ps.setDate(2, obj.getSubmitted());
			ps.setDate(3, obj.getResolved());
			ps.setString(4, obj.getDescription());
			ps.setInt(5, obj.getAuthor());
			ps.setInt(6, obj.getResolver());
			ps.setInt(7, obj.getStatus());
			ps.setInt(8, obj.getType());
			
			int numRows = ps.executeUpdate();
			if(numRows > 0) {
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

	public Reimbursement update(Reimbursement obj) {
		
		return null;
	}

}

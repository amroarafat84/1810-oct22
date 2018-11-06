package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Account;
import com.revature.util.ConnectionFactory;

public class AccountDao implements Dao<Account,Integer>{

	@Override
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<Account>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM BANK_ACCOUNT"; //no semicolon
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB Query
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Account temp = new Account();
				temp.setId(rs.getInt(1));
				temp.setType(rs.getInt(2));
				temp.setUserID(rs.getInt(3));
				temp.setBalance(rs.getDouble(4));
				accounts.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public Account findByID(Integer id) {
		Account acc = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				acc = new Account();
				acc.setId(rs.getInt(1));
				acc.setType(rs.getInt(2));
				acc.setUserID(rs.getInt(3));
				acc.setBalance(rs.getDouble(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}
	
	@Override
	public Account findByEmail(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account insert(Account obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO BANK_ACCOUNT(ACCOUNT_TYPE,USER_ID,BALANCE) VALUES(?,?,?)";
			String[] keyNames = {"ACCOUNT_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql,keyNames);
			ps.setInt(1, obj.getType());
			ps.setInt(2, obj.getUserID());
			ps.setDouble(3, obj.getBalance());
			
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

	@Override
	public Account update(Account obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, obj.getBalance());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public void delete(Account obj) {
		// TODO Auto-generated method stub
		
	}

}

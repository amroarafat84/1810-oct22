package com.ex.pojos;

public class User {
	private String username;
	private String password;
	private String data;
	
	public User() {}

	

	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	public User(String username, String password, String data) {
		super();
		this.username = username;
		this.password = password;
		this.data = data;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", data=" + data + "]";
	}



	public void setPassword(String password) {
		this.password = password;
	}



	
}

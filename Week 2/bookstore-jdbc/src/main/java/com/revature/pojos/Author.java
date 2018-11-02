package com.revature.pojos;

public class Author {
	
	private int id;
	private String FirstName;
	private String LastName;
	private String bio;
	
	public Author() {}
	
	public Author(String firstName) {
		super();
		FirstName = firstName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", bio=" + bio + "]";
	}

}

package com.revature.pojos;

public class Author {
    
    private int id;
    private String firstName;
    private String lastName;
    private String bio;
    
    public Author() {}

    public Author(String firstName, String lastName, String bio) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Author [id=" ++ id + ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + "]";
    }    
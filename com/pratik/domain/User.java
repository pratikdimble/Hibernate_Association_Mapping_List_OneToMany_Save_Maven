package com.pratik.domain;

import java.util.Set;

public class User {
	private int userId;
	private String firstName;
	private Set<PhoneNumber> phones;
	
	 public User() {
		System.out.println("User:0-param constructor");
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Set<PhoneNumber> getPhones() {
		return phones;
	}
	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName+"\n" ;
	}
}

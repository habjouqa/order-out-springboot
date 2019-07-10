package com.orderout.orderout.domain;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "users")
public class User implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1450082544038449165L;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column
	private String password;
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column
	private boolean active = false;

	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "users")
	private List<Group> groups;
	
	public User(String currentUserId) {
		this.setEmail(currentUserId);
	}

	public User() {
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

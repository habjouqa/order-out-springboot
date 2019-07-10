package com.orderout.orderout.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "group_team")
public class Group implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "groups_users",
        joinColumns = {@JoinColumn(name = "id")},
        inverseJoinColumns = {@JoinColumn(name = "email")})
    private List<User> users ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		if(users==null) {
			System.out.println("It's Null ");
			return new ArrayList<User>();
		}
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

	

}

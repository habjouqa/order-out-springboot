package com.orderout.orderout.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "group_orders")
public class GroupOrder {

	public GroupOrder() {
	}

	public GroupOrder(int id) {
		this.id=id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "email")
	private User owner;
	private Date endDate;
	private String orderMethod;
	private String description;
	private Long numberOfOrders;

	@ManyToMany
	@JoinTable(name = "group_order_users", joinColumns = { @JoinColumn(name = "id", unique = true) }, inverseJoinColumns = {
			@JoinColumn(name = "email") }, uniqueConstraints = @UniqueConstraint(columnNames = {"id", "email"}))
	private List<User> users;

	@JsonManagedReference
	@OneToMany(mappedBy="groupOrder",cascade= CascadeType.ALL,fetch = FetchType.EAGER)
	private List<UserOrder> userOrder;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(String orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserOrder> getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(List<UserOrder> userOrder) {
		this.userOrder = userOrder;
	}

	public Long getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(Long numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}
	
	

}

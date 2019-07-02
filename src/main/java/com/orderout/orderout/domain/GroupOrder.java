package com.orderout.orderout.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_orders")
public class GroupOrder {

	public GroupOrder() {
	}

	@Id
	@GeneratedValue
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "email")
	private User owner;
	private Date endDate;
	private String orderMethod;

	private String description;

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

}

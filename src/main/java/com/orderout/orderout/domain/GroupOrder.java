package com.orderout.orderout.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

<<<<<<< HEAD
@Entity
=======
@Entity
>>>>>>> branch 'master' of https://github.com/habjouqa/order-out-springboot.git
@Table(name = "group_orders")
public class GroupOrder {

	public GroupOrder() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
<<<<<<< HEAD

	
	@OneToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;
	
	@OneToOne
=======

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;

	@OneToOne(cascade = CascadeType.ALL)
>>>>>>> branch 'master' of https://github.com/habjouqa/order-out-springboot.git
	@JoinColumn(name = "user_id", referencedColumnName = "email")
	private User owner;
<<<<<<< HEAD
	
	private Date endDate;
	private String orderMethod;
=======
	private Date endDate;
	private String orderMethod;

>>>>>>> branch 'master' of https://github.com/habjouqa/order-out-springboot.git
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

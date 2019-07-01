package com.orderout.orderout.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="group_order")
public class GroupOrder {
	
	
	public GroupOrder() {
	}
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resturnt_id", referencedColumnName = "id")
	private Resturant resturnt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "email")
	private User user;
	private Date endDate;
	private String orderMethod;
	
	private String description;
	
	
	public GroupOrder(int id, Resturant resturnt, User user, Date endDate, String orderMethod) {
		super();
		this.id = id;
		this.resturnt = resturnt;
		this.user = user;
		this.endDate = endDate;
		this.orderMethod = orderMethod;
	}
	@Override
	public String toString() {
		return "GroupOrder [id=" + id + ", resturnt=" + resturnt + ", user=" + user + ", endDate=" + endDate
				+ ", orderMethod=" + orderMethod + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Resturant getResturnt() {
		return resturnt;
	}
	public void setResturnt(Resturant resturnt) {
		this.resturnt = resturnt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

package com.orderout.orderout.domain;

import javax.persistence.*;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String pictureUrl;
	private String phoneNumber;
	private String address;
	private String status;

	@OneToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private Menu menu;

	public Restaurant() {
	}

	public Restaurant(int id, String name, String phoneNumber, String address, Menu menu) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.menu = menu;
	}

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", menu=" + menu + "]";
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}

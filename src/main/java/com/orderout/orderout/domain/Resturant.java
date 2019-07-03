package com.orderout.orderout.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name="resturant")
public class Resturant implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String resturantName;
	private String phoneNumber;
	private String address;
	private String status;
	
	@OneToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private Menu menu;
	
	private String imgURL;
	
	

	public Resturant() {
	}

	public Resturant(int id, String resturantName, String phoneNumber, String address, Menu menu) {
		super();
		this.id = id;
		this.resturantName = resturantName;
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

	public String getResturantName() {
		return resturantName;
	}

	public void setResturantName(String resturantName) {
		this.resturantName = resturantName;
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
	
	
	

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	@Override
	public String toString() {
		return "Resturant [id=" + id + ", resturantName=" + resturantName + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", menu=" + menu + "]";
	}
	
	
	

}

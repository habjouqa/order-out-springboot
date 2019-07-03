package com.orderout.orderout.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="menu")
public class Menu {
	
	@Id
	@GeneratedValue
	private int id;
	
	@JsonIgnore
	@OneToMany(targetEntity=Product.class, mappedBy="menu", cascade = CascadeType.ALL)
	private List<Product> products;

	
	
	public Menu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

	
}

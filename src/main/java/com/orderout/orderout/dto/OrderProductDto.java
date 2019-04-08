package com.orderout.orderout.dto;

import com.orderout.orderout.domain.User;
import com.orderout.orderout.model.Product;

public class OrderProductDto {

    private User user;
    private Product product;
    private Integer quantity;
    
    public User getUser() {
    	return user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

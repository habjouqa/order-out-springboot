package com.orderout.orderout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.service.GroupOrderBusiness;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GroupOrdersController {

	@Autowired
	GroupOrderBusiness service;
	
	
	@GetMapping("/group-orders")
	public Iterable<GroupOrder> getAllGroupOrders(){
	//	return service.getAllGroupOrders();
		
		System.out.println("Hello ");
		return service.getAllGroupOrders();	}
}

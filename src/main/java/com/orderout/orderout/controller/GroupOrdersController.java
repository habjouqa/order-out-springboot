package com.orderout.orderout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.service.GroupOrderBusiness;

@RestController
public class GroupOrdersController {

	@Autowired
	GroupOrderBusiness service;
	
	
	@GetMapping("/group-order")
	public Iterable<GroupOrder> getAllGroupOrders(){
	//	return service.getAllGroupOrders();
		
		System.out.println("Hello ");
		return service.getAllGroupOrders();	}
}

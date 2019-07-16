package com.orderout.orderout.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.domain.ApiResponse;
import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.service.GroupOrderBusiness;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GroupOrdersController {

	@Autowired
	GroupOrderBusiness service;

	@GetMapping("/group-orders")
	public Iterable<GroupOrder> getAllGroupOrders() {
		System.out.println("### getAllGroupOrders ###");
		return service.getAllGroupOrders();
	}

	@PostMapping("/group-orders")
	private GroupOrder create(@RequestBody GroupOrder GroupOrder) {
		System.out.println("### Create Group Order ### " + GroupOrder);
		return service.create(GroupOrder);
	}
	
	
	@PostMapping("/group-orders/AddUser")
	private GroupOrder addUserToGroup(@RequestParam String groupId) {
		System.out.println("### groupId Group Order ### " + groupId);
		return service.addUserToGroup(Integer.valueOf(groupId));
	}
	
	
	

	@RequestMapping(value = "/order_deadline/{id}", method = RequestMethod.GET)
	public ApiResponse<String> getDateOrderDeadline(@PathVariable Integer id) {

		String orderDeadline = service.getDateOrderDeadline(id);
		String currentTime = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a").format(new Date());

		return new ApiResponse<>(HttpStatus.OK.value(), "Date Order Dead line Retrive Successfully ",
				"{\"orderDeadline\" : \"" + orderDeadline + "\", \"currentTime\" : \"" + currentTime + "\"}");

	}

}

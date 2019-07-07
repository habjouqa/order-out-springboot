package com.orderout.orderout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.domain.Restaurant;
import com.orderout.orderout.service.ResturantBusiness;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RestaurantController {

	@Autowired
	ResturantBusiness service;

	@GetMapping("/restaurant")
	public Iterable<Restaurant> getAllRestaurant() {
		return service.getAllResturants();
	}
}

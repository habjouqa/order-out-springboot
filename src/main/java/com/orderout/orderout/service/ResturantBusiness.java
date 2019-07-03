package com.orderout.orderout.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.dao.ResturantRepository;
import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.Restaurant;
@Service
public class ResturantBusiness {
	
	@Autowired
	ResturantRepository service;
	
	public Iterable<Restaurant> getAllResturants() {

		return service.findAll();

	}

}

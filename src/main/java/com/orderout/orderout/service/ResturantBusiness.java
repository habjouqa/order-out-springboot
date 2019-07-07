package com.orderout.orderout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.ResturantRepository;
import com.orderout.orderout.domain.Restaurant;


@Service
public class ResturantBusiness {

	@Autowired
	ResturantRepository service;

	public Iterable<Restaurant> getAllResturants() {

		return service.findAll(Sort.by(Direction.ASC, "name"));

	}

}

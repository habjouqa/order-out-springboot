package com.orderout.orderout.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.domain.GroupOrder;

@Service
public class GroupOrderBusiness {
	
	@Autowired
	GroupOrderRepository service;
	
	public Iterable<GroupOrder> getAllGroupOrders() {

		return service.findAll();

	}

}

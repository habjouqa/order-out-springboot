package com.orderout.orderout.service;

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
	
	public  GroupOrder create(GroupOrder groupOrder) {
		return service.save(groupOrder);

	}

	public String getDateOrderDeadline(Integer id) {
		return service.findById(id).get().getEndDate().toString();
	}

}

package com.orderout.orderout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.domain.GroupOrder;

@Service
public class GroupOrderBusiness {

	@Autowired
	GroupOrderRepository groupOrderRepository;

	public Iterable<GroupOrder> getAllGroupOrders() {
		return groupOrderRepository.findAllActive();
	}

	public GroupOrder create(GroupOrder groupOrder) {
		return groupOrderRepository.save(groupOrder);

	}

	public String getDateOrderDeadline(Integer id) {
		return groupOrderRepository.findById(id).get().getEndDate().toString();
	}

}

package com.orderout.orderout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.dao.UserDao;
import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.User;

@Service
public class GroupOrderBusiness {

	@Autowired
	GroupOrderRepository groupOrderRepository;

	@Autowired
	private UserDao userDao;

	@Autowired
	OrderService orderService;

	public Iterable<GroupOrder> getAllGroupOrders() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			
		}
		Iterable<GroupOrder> groupsOrders=groupOrderRepository.findAllActive(authentication.getName());
		for (GroupOrder groupOrder : groupsOrders) {
			groupOrder.setNumberUsers(orderService.getTotalByGroupId(groupOrder.getId()));
		}
		return groupsOrders;
	}

	public Iterable<GroupOrder> getGroupOrderByEmail() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Iterable<GroupOrder> groupsOrders=groupOrderRepository.findByOwnerEmail(authentication.getName());
			for (GroupOrder groupOrder : groupsOrders) {
				groupOrder.setNumberUsers(orderService.getTotalByGroupId(groupOrder.getId()));
			}
			return groupsOrders;	
		}
		return null; 

	}

	public GroupOrder create(GroupOrder groupOrder) {
		
		if(!groupOrder.getUsers().contains(new User(groupOrder.getOwner().getEmail()))) {
			groupOrder.getUsers().add(new User(groupOrder.getOwner().getEmail()));
			groupOrderRepository.save(groupOrder);
		}
		return groupOrderRepository.save(groupOrder);

	}

	public String getDateOrderDeadline(Integer id) {
		return groupOrderRepository.findById(id).get().getEndDate().toString();
	}

	public GroupOrder getGroupOrderById(Integer id) {
		return groupOrderRepository.findById(id).get();
	}

	public GroupOrder addUserToGroup(Integer id) {
		GroupOrder groupOrder =groupOrderRepository.findById(id).get();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserId = authentication.getName();

			if(!groupOrder.getUsers().contains(new User(currentUserId))) {
				groupOrder.getUsers().add(new User(currentUserId));
				groupOrderRepository.save(groupOrder);
			}


		}

		return groupOrder;

	}

}

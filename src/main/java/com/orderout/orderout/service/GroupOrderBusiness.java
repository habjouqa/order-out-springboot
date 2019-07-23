package com.orderout.orderout.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.dao.UserDao;
import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.OrderSummary;
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
			groupOrder.setNumberOfOrders(orderService.getTotalByGroupId(groupOrder.getId()));
		}
		return groupsOrders;
	}
	

	public Iterable<GroupOrder> getGroupOrderByEmail() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Iterable<GroupOrder> groupsOrders=groupOrderRepository.findByOwnerEmail(authentication.getName());
			for (GroupOrder groupOrder : groupsOrders) {
				groupOrder.setNumberOfOrders(orderService.getTotalByGroupId(groupOrder.getId()));
				
				Iterable<OrderSummary> groupsOrdersSummary=groupOrderRepository.getGroupSummary(authentication.getName());
				List<OrderSummary> listOrderSummary=null;
				
				for (OrderSummary orderSummary : groupsOrdersSummary) {
						if(groupOrder.getId()==Integer.valueOf(orderSummary.getGroupOrder())) {
							listOrderSummary=groupOrder.getOrderSummary();
							listOrderSummary.add(orderSummary);
						}
						groupOrder.setOrderSummary(listOrderSummary);
				}
				
			}
			
			
			
			return groupsOrders;	
		}
		return null; 

	}


	public Map<String, List<OrderSummary>> getGroupOrderSummary() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Iterable<OrderSummary> groupsOrders=groupOrderRepository.getGroupSummary(authentication.getName());

		
			Map<String,List<OrderSummary>> result = new HashMap<String,List<OrderSummary>>();
			for (OrderSummary orderSummary : groupsOrders) {
				
				if(!result.containsKey(orderSummary.getGroupOrder())) {
				
					result.put(orderSummary.getGroupOrder(),Lists.newArrayList(orderSummary));
				
				}else {
					List<OrderSummary> orderSummaryTemp=result.get(orderSummary.getGroupOrder());
					orderSummaryTemp.add(orderSummary);
					result.put(orderSummary.getGroupOrder(),orderSummaryTemp);

				}
				
			}
				
					
			return result;	
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

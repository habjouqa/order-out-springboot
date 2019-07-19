package com.orderout.orderout.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

import org.hibernate.Criteria;
import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderout.orderout.domain.UserOrder;
import com.orderout.orderout.domain.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;

    
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<UserOrder> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public UserOrder create(UserOrder order) {
        order.setDateCreated(LocalDate.now());

        return this.orderRepository.save(order);
    }

    @Override
    public void update(UserOrder order) {
        this.orderRepository.save(order);
    }


    public Long getTotalByGroupId(int groupId) {
    	return this.orderRepository.countByGroupOrderId(groupId);
    }
    
	public @NotNull Iterable<UserOrder> getOrdersByUser(String email) {
	    return this.orderRepository.findByOrderProductsPkUserEmail(email);
	}


}

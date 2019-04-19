package com.orderout.orderout.domain;

import com.orderout.orderout.domain.Order;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	
	public @NotNull Iterable<Order> findByOrderProductsPkUserEmail(String email);
}

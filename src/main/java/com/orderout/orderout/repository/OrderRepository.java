package com.orderout.orderout.repository;

import com.orderout.orderout.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

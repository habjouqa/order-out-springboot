package com.orderout.orderout.domain;

import com.orderout.orderout.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

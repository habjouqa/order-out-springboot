package com.orderout.orderout.service;

import com.orderout.orderout.domain.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();
    
    public @NotNull Iterable<Order> getOrdersByUser(String email);
    
    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}

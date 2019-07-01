package com.orderout.orderout.service;

import com.orderout.orderout.domain.UserOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {

    @NotNull Iterable<UserOrder> getAllOrders();
    
    public @NotNull Iterable<UserOrder> getOrdersByUser(String email);
    
    UserOrder create(@NotNull(message = "The order cannot be null.") @Valid UserOrder order);

    void update(@NotNull(message = "The order cannot be null.") @Valid UserOrder order);
}

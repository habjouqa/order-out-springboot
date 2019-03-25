package com.orderout.orderout.repository;

import com.orderout.orderout.model.OrderProduct;
import com.orderout.orderout.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}

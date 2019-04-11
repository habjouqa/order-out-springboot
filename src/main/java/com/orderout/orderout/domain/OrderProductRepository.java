package com.orderout.orderout.domain;

import com.orderout.orderout.domain.OrderProduct;
import com.orderout.orderout.domain.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}

package com.orderout.orderout.domain;

import com.orderout.orderout.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

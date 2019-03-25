package com.orderout.orderout.repository;

import com.orderout.orderout.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

package com.orderout.orderout.domain;

import com.orderout.orderout.domain.Product;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@Query("SELECT u FROM Product u WHERE u.menu.id = ?1")
	public @NotNull Iterable<Product> getAllProductsByMenuId(Long menuId);
}


<<<<<<< HEAD
package com.orderout.orderout.service;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderout.orderout.domain.Product;
import com.orderout.orderout.domain.ProductRepository;
import com.orderout.orderout.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public @NotNull Iterable<Product> getAllProductsByMenuId(Long menuId) {
		return productRepository.getAllProductsByMenuId(menuId);
	}
}
=======
package com.orderout.orderout.service;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderout.orderout.domain.Product;
import com.orderout.orderout.domain.ProductRepository;
import com.orderout.orderout.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public @NotNull Iterable<Product> getAllProductsByMenuId(Long menuId) {
		return productRepository.getAllProductsByMenuId(menuId);
	}
}
>>>>>>> branch 'master' of https://github.com/habjouqa/order-out-springboot.git

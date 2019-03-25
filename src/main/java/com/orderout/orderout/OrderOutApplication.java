package com.orderout.orderout; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.orderout.orderout.model.Product;
import com.orderout.orderout.service.ProductService;



@SpringBootApplication
public class OrderOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOutApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
            productService.save(new Product(1L, "7aleva Jebneh", 0.37, "assets/images/slider-01.jpg"));
            productService.save(new Product(2L, "7aleva Ba6a6a", 0.32, "assets/images/slider-01.jpg"));
            productService.save(new Product(3L, "7aleva La7meh", 0.35, "assets/images/slider-01.jpg"));
        };
    }
}

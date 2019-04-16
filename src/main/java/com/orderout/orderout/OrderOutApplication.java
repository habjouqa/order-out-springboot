package com.orderout.orderout; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOutApplication.class, args);
		System.out.println(" ##########  BLA ##########");
	}
	
	void test () {
		// test
		int x = 1 + 5;
		System.out.println("1+5 = " + x);
	}

}

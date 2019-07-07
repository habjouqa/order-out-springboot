package com.orderout.orderout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderout.orderout.domain.Restaurant;

public interface ResturantRepository extends JpaRepository<Restaurant, Integer> {

}

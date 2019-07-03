package com.orderout.orderout.dao;

import org.springframework.data.repository.CrudRepository;

import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.Restaurant;

public interface ResturantRepository extends CrudRepository<Restaurant, Integer> {

}

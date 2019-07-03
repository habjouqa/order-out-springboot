package com.orderout.orderout.dao;

import org.springframework.data.repository.CrudRepository;

import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.Resturant;

public interface ResturantRepository extends CrudRepository<Resturant, Integer> {

}

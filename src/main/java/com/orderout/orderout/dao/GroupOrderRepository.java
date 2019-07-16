package com.orderout.orderout.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderout.orderout.domain.GroupOrder;

@Repository
public interface GroupOrderRepository extends CrudRepository<GroupOrder, Integer> {

}

package com.orderout.orderout.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderout.orderout.domain.GroupOrder;

@Repository
public interface GroupOrderRepository extends CrudRepository<GroupOrder, Integer> {

	@Query("select u from GroupOrder u where u.endDate > CURRENT_DATE")  
	Iterable<GroupOrder> findAllActive();

}

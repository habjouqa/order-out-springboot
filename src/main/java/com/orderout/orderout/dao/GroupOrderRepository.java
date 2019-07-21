package com.orderout.orderout.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderout.orderout.domain.GroupOrder;

@Repository
public interface GroupOrderRepository extends CrudRepository<GroupOrder, Integer> {

	@Query("select groupOrder from GroupOrder groupOrder JOIN groupOrder.users user where groupOrder.endDate > CURRENT_TIMESTAMP and user.email=:email")  
	Iterable<GroupOrder> findAllActive(String email);
	
	public Iterable<GroupOrder> findByOwnerEmail(String email);
	//public Iterable<GroupOrder> findByUsersEmail(String email);


}

package com.orderout.orderout.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.OrderSummary;

@Repository
public interface GroupOrderRepository extends CrudRepository<GroupOrder, Integer> {

	@Query("select distinct groupOrder from GroupOrder groupOrder JOIN groupOrder.users user where groupOrder.endDate > CURRENT_TIMESTAMP and user.email=:email")  
	Iterable<GroupOrder> findAllActive(String email);
	
	
	@Query(nativeQuery=true,value = "select go.id as GroupOrder, go.description as Description, r.name as Restaurant, p.name as productName, SUM(op.quantity) as numberItems,p.price as price, (SUM(op.quantity) * p.price) as total \r\n" + 
			"from order_out.order_product as op, order_out.product as p, order_out.restaurant as r, order_out.group_orders as go, order_out.user_orders as uo\r\n" + 
			"where p.id = op.product_id \r\n" + 
			"and op.order_id=uo.id \r\n" + 
			"and r.id=go.restaurant_id and p.menu_id=r.menu_id and uo.group_id=go.id\r\n" + 
			"and go.user_id=:email \r\n" + 
			"group by GroupOrder, Description, Restaurant, productName,Price;")  
	Iterable<OrderSummary> getGroupSummary(String email);
	
	
	
	public Iterable<GroupOrder> findByOwnerEmail(String email);
	

	//public Iterable<GroupOrder> findByUsersEmail(String email);


}

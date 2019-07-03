package com.orderout.orderout.service;

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/habjouqa/order-out-springboot.git
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orderout.orderout.dao.GroupOrderRepository;
import com.orderout.orderout.domain.GroupOrder;

@Service
public class GroupOrderBusiness {
	
	@Autowired
	GroupOrderRepository service;
	
	public Iterable<GroupOrder> getAllGroupOrders() {
		return service.findAll();
	}
	
	
	public  GroupOrder create(GroupOrder groupOrder) {
		return service.save(groupOrder);

	}

	
}

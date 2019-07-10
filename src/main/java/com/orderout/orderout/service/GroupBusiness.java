package com.orderout.orderout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.GroupDao;
import com.orderout.orderout.domain.Group;
import com.orderout.orderout.domain.User;

@Service
public class GroupBusiness {
	
	@Autowired
	GroupDao service;
	
	@Autowired
	private UserService userService;
	
	public Group save(Group group) {
			
			return service.save(group);
	}
	
	
	
	public void addUserToGroup(String userId,String groupId) {
		System.out.println(" addUserToGroup "+userId +" "+ groupId);
	
		Group group=service.findById(Integer.valueOf(groupId)).get();
		User user=userService.findByEmail(userId);
		
		group.getUsers().add(user);
		
		service.save(group);

	}

}

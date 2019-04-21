package com.orderout.orderout.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderout.orderout.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

	@Query("select u from User u where u.email=?1 and u.active =?2")  
	User findByStatus(String email, boolean active);
	
	User findByEmail(String email);

//	@Query("select u from User u where u.active = '0' and u.email=?1")  
//	User findInactiveByEmail(String email);
}

package com.orderout.orderout.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orderout.orderout.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

	  User findByEmail(String username);
}

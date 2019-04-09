package com.orderout.orderout.services;

import java.util.List;

import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;

public interface UserService {

	User save(UserDto user);

	List<User> findAll();

	void delete(String id);

	User findByEmail(String email, String active);

//	User findInactiveByEmail(String email);

//	User findById(String email);

	UserDto update(UserDto userDto);

	void activate(String email);
}

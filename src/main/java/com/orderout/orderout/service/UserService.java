package com.orderout.orderout.service;

import java.util.List;

import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;

public interface UserService {

	User save(UserDto user);

	List<User> findAll();

	void delete(String id);

	User findByEmail(String email, boolean active);

//	User findInactiveByEmail(String email);

//	User findById(String email);

	UserDto update(UserDto userDto);

	void activate(String email);
}

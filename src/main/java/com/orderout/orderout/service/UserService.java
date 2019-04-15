package com.orderout.orderout.service;

import java.util.List;

import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;

public interface UserService {

	User save(UserDto user);

	List<User> findAll();

	void delete(String id);

	User findByEmail(String email, boolean active);

	User update(User userDto);

	void activate(String email);

	void sendVerification(String email);
}

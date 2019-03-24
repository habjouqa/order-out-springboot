package com.orderout.orderout.services;


import java.util.List;

import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;
public interface UserService {

	 User save(UserDto user);
	    List<User> findAll();
	    void delete(int id);

	    User findOne(String username);

	    User findById(int id);

	    UserDto update(UserDto userDto);
}

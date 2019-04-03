package com.orderout.orderout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.domain.ApiResponse;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;
import com.orderout.orderout.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	 @Autowired
	    private UserService userService;

	 	@RequestMapping("/signup")
	    @PostMapping
	    public ApiResponse<User> saveUser(@RequestBody UserDto user){
	        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
	    }

	 	@RequestMapping("/users")
	    @GetMapping
	    public ApiResponse<List<User>> listUser(){
	        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
	    }

	    @GetMapping("/users/{id}")
	    public ApiResponse<User> getOne(@PathVariable int id){
	        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
	    }

	    @PutMapping("/users/{id}")
	    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
	        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
	    }

	    @DeleteMapping("/users/{id}")
	    public ApiResponse<Void> delete(@PathVariable int id) {
	        userService.delete(id);
	        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
	    }



}

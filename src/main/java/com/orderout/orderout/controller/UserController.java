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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ApiResponse<User> saveUser(@RequestBody UserDto user) {
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.", userService.save(user));

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@RequestMapping("/users")
	@GetMapping
	public ApiResponse<List<User>> listUser() {
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", userService.findAll());

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@GetMapping("/users/{email}")
	public ApiResponse<User> getOne(@PathVariable String email) {
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", userService.findById(email));

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@PutMapping("/users/{email}")
	public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.update(userDto));

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@GetMapping("/activate/{email}")
	public ApiResponse<Void> activate(@PathVariable String email) {
		try {
			System.out.println(" ------------------------ ");
			userService.activate(email);
			return new ApiResponse<>(HttpStatus.OK.value(), "User activated successfully.", null);

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@DeleteMapping("/users/{email}")
	public ApiResponse<Void> delete(@PathVariable String email) {
		try {
			userService.delete(email);
			return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@GetMapping("/isExistsEmail")
	public ApiResponse<User> isExistsEmail(@RequestParam String email) {
		try {
			User user = userService.findById(email);
			return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", user != null ? true : false);

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

}

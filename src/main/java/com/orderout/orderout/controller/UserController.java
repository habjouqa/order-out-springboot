package com.orderout.orderout.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.orderout.orderout.constants.Constants;
import com.orderout.orderout.domain.ApiResponse;
import com.orderout.orderout.domain.ConfirmationToken;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;
import com.orderout.orderout.service.ConfirmationTokenRepository;
import com.orderout.orderout.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
			return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",
					userService.findByEmail(email, Constants.ACTIVE));

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@PutMapping("/users/{email}")
	public ApiResponse<UserDto> update(@RequestBody User userDto) {
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.update(userDto));

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@GetMapping("/activate")
	public ApiResponse<Void> activate(@RequestParam String token) {
		try {
			System.out.println("token: "+ token);

			// Find the user associated with the reset token Optional<ConfirmationToken>
			Optional<ConfirmationToken> confirm = confirmationTokenRepository.findByConfirmationToken(token.trim());
			System.out.println("######### confirm : " + confirm.isPresent());
			
			if (confirm.isPresent()) {
				userService.activate(confirm.get().getUser().getEmail());
				System.out.println("############ confirm.get().getUser().getEmail() : " + confirm.get().getUser().getEmail());
				System.out.println("############ confirm.get() : " + confirm.get());
				confirmationTokenRepository.delete(confirm.get());
				return new ApiResponse<>(HttpStatus.OK.value(), "User activated successfully.", null);
			}
			else {
				System.out.println("confirmationToken it's Not Exits");
				return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "confirmationToken not found.", null);
			}
		} catch (Exception e) {
			System.err.println("ererer");
			e.printStackTrace();
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
			User user = userService.findByEmail(email, Constants.ACTIVE);
			return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", user != null ? true : false);

		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null);
		}
	}

	@GetMapping("/sendVerification")
	public void sendVerification(@RequestParam String email) {

		userService.sendVerification(email);
		System.out.println(">> >> >> >> Send Verfication ");

	}

	@PutMapping("/reset")
	public ApiResponse<User> setNewPassword(@RequestBody String data) {

		JSONObject jsonObj = new JSONObject(data);

		String token = (String) jsonObj.get("token");
		String password = (String) jsonObj.get("password");

		// Find the user associated with the reset token Optional<ConfirmationToken>
		Optional<ConfirmationToken> confirm = confirmationTokenRepository.findByConfirmationToken(token);

		if (confirm.isPresent()) {

			User resetUser = confirm.get().getUser();
			resetUser.setPassword(bCryptPasswordEncoder.encode(password));
			userService.update(resetUser);

			confirmationTokenRepository.delete(confirm.get());
			System.out.println("confirmationToken It's Exits");
			return new ApiResponse<>(HttpStatus.OK.value(), "confirmationToken fetched successfully.",
					resetUser != null ? true : false);

		} else {

			System.out.println("confirmationToken it's Not Exits");

			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "confirmationToken not found.", null);
		}

	}

}

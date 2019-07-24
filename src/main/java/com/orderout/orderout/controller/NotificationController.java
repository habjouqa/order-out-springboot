package com.orderout.orderout.controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.orderout.orderout.service.NotificationBusiness;
import com.orderout.orderout.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NotificationController {
	@Autowired
	private NotificationBusiness notificationBusiness;


	@RequestMapping("/order-received-notify/{groupId}")
	@PostMapping
	public void sendNotifyOrderReceived(@PathVariable int groupId) {
		try {
			
			notificationBusiness.sendNotifyOrderReceived(groupId);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

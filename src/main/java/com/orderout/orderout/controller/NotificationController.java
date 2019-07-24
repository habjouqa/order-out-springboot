package com.orderout.orderout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.service.NotificationBusiness;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NotificationController {
	@Autowired
	private NotificationBusiness notificationBusiness;


	@RequestMapping("group-orders/order-received-notify/{groupId}")
	@PostMapping
	@CrossOrigin
	public void sendNotifyOrderReceived(@PathVariable int groupId) {
		try {
			
			notificationBusiness.sendNotifyOrderReceived(groupId);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

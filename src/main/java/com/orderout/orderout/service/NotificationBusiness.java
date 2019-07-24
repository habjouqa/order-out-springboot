package com.orderout.orderout.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderout.orderout.constants.Template;
import com.orderout.orderout.domain.EmailDto;
import com.orderout.orderout.domain.OrderProduct;
import com.orderout.orderout.domain.User;

@Service
public class NotificationBusiness {


	@Autowired
	GroupOrderBusiness service;


	@Autowired
	SendGridService sendGridService;


	public void sendNotifyOrderReceived(int groupId) {

		List<User> usersOrder=service.getGroupOrderById(groupId).getUserOrder().stream().findAny().get().getOrderProducts().stream().map(OrderProduct::getUser).distinct().collect(Collectors.toList());

		for(User user :usersOrder) {
			try {

				EmailDto emailDto = new EmailDto();
				emailDto.setEmailSubject(Template.SUBJECT_INFORM_ORDER_ARRAIVED);
				emailDto.setFromEmail("userspsdev2@gmail.com");
				emailDto.setFromName("Order Out");
				emailDto.setToEmail(user.getEmail());
				emailDto.setMessage(Template.INFORM_ORDER_ARRAIVED);
				sendGridService.sendMail(emailDto);

			}catch (Exception e) {
				e.printStackTrace();

				System.out.println("Exception IN >> NotificationBusiness >> sendNotifyOrderReceived User "+user.getEmail());
			}
		}

	}
}

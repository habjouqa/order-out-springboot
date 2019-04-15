package com.orderout.orderout.service;

import com.orderout.orderout.domain.EmailDto;
import com.sendgrid.Email;


public interface SendGridService {

	/**
	 * Send email
	 * 
	 * @param emailPojo
	 */
	public String sendMail(EmailDto emailPojo);
}

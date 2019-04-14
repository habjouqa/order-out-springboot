package com.orderout.orderout.service;

import com.orderout.orderout.domain.EmailDto;
import com.orderout.orderout.domain.OrderProduct;
import com.orderout.orderout.domain.OrderProductRepository;
import com.orderout.orderout.service.MailService;
import com.orderout.orderout.constants.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	private OrderProductRepository orderProductRepository;
	@Autowired
	private MailService mail;
	public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}

	@Override
	public OrderProduct create(OrderProduct orderProduct) {

		try {

			
			EmailDto emailDto=new EmailDto();
			emailDto.setEmailSubject(Template.ORDER_MESSAGE);
			emailDto.setFromEmail("userspsdev2@gmail.com");
			emailDto.setFromName("Order Out");
			
			emailDto.setToEmail(orderProduct.getPk().getUser().getEmail());
			emailDto.setMessage(Template.ORDER_MESSAGE);
			
			//mail.sendEmail(orderProduct.getPk().getUser(),Template.ORDER_MESSAGE,Template.ORDER_MESSAGE);

		}catch (Exception e) {
			System.err.println(" ########### SEND EMAIL FAILD ###########3");
			e.printStackTrace();

		}
			return this.orderProductRepository.save(orderProduct);
		
	}
}

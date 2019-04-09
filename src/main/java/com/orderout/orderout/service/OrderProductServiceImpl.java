package com.orderout.orderout.service;

import com.orderout.orderout.model.OrderProduct;
import com.orderout.orderout.repository.OrderProductRepository;
import com.orderout.orderout.services.MailService;
import com.orderout.orderout.services.constant.Template;

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

			System.out.println("orderProduct.getPk().getUser() >> >>"+orderProduct.getPk().getUser());
			mail.sendEmail(orderProduct.getPk().getUser(),Template.ORDER_MESSAGE,Template.ORDER_MESSAGE);

		}catch (Exception e) {
			System.err.println(" ########### SEND EMAIL FAILD ###########3");
			e.printStackTrace();

		}
			return this.orderProductRepository.save(orderProduct);
		
	}
}

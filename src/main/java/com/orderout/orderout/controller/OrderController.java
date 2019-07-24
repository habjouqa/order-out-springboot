package com.orderout.orderout.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderout.orderout.domain.GroupOrder;
import com.orderout.orderout.domain.OrderProduct;
import com.orderout.orderout.domain.OrderProductDto;
import com.orderout.orderout.domain.OrderStatus;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserOrder;
import com.orderout.orderout.exception.ResourceNotFoundException;
import com.orderout.orderout.service.ConfigurationService;
import com.orderout.orderout.service.OrderProductService;
import com.orderout.orderout.service.OrderService;
import com.orderout.orderout.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderProductService orderProductService;

	@Autowired
	ConfigurationService configurationService;

	public OrderController(ProductService productService, OrderService orderService,
			OrderProductService orderProductService) {
		this.productService = productService;
		this.orderService = orderService;
		this.orderProductService = orderProductService;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/orders", method = RequestMethod.GET)
	public @NotNull Iterable<UserOrder> list(@RequestParam String email) {
		return this.orderService.getOrdersByUser(email);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/orders/group-order/count", method = RequestMethod.GET)
	public @NotNull Long list(@RequestParam int groupId) {
		return this.orderService.getTotalByGroupId(groupId);
	}

	@RequestMapping(value = "/api/orders", method = RequestMethod.POST)
	public ResponseEntity<UserOrder> create(@RequestBody OrderForm form) {
		List<OrderProductDto> formDtos = form.getProductOrders();
		validateProductsExistence(formDtos);
		UserOrder order = new UserOrder();
		order.setGroupOrder(new GroupOrder(form.getGroupOrder().getId()));
		order.setStatus(OrderStatus.PAID.name());
		order = this.orderService.create(order);

		List<OrderProduct> orderProducts = new ArrayList<>();
		String currentUserId = "";

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserId = authentication.getName();

			System.out.println("Hello" + currentUserId);
		}

		for (OrderProductDto dto : formDtos) {
			dto.setUser(new User(currentUserId));
			orderProducts.add(orderProductService.create(new OrderProduct(order,
					productService.getProduct(dto.getProduct().getId()), dto.getQuantity(), dto.getUser())));
		}

		order.setOrderProducts(orderProducts);

		this.orderService.update(order);

		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(order.getId()).toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/orders", method = RequestMethod.DELETE)
	public void delete(@RequestBody UserOrder userOrder) {

		this.orderService.delete(userOrder);
	}

	private void validateProductsExistence(List<OrderProductDto> orderProducts) {
		List<OrderProductDto> list = orderProducts.stream()
				.filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not found");
		}
	}

	public static class OrderForm {

		private GroupOrder groupOrder;
		private List<OrderProductDto> productOrders;

		public List<OrderProductDto> getProductOrders() {
			return productOrders;
		}

		public void setProductOrders(List<OrderProductDto> productOrders) {
			this.productOrders = productOrders;
		}

		public GroupOrder getGroupOrder() {
			return groupOrder;
		}

		public void setGroupOrder(GroupOrder groupOrder) {
			this.groupOrder = groupOrder;
		}
	}
}

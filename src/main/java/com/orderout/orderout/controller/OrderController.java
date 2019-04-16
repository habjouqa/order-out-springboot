package com.orderout.orderout.controller;

import com.orderout.orderout.domain.OrderProductDto;
import com.orderout.orderout.exception.ResourceNotFoundException;
import com.orderout.orderout.constants.Constants;
import com.orderout.orderout.domain.ApiResponse;
import com.orderout.orderout.domain.Order;
import com.orderout.orderout.domain.OrderProduct;
import com.orderout.orderout.domain.OrderStatus;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.service.ConfigurationService;
import com.orderout.orderout.service.OrderProductService;
import com.orderout.orderout.service.OrderService;
import com.orderout.orderout.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    ProductService productService;
    OrderService orderService;
    OrderProductService orderProductService;
    
    @Autowired
    ConfigurationService configurationService;

    public OrderController(ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

   
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/api/orders", method = RequestMethod.GET)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @RequestMapping(value ="/api/orders", method = RequestMethod.POST)
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(
            		new OrderProduct(order, productService.getProduct(dto.getProduct().getId()), dto.getQuantity(), dto.getUser())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
    


    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
    
    @RequestMapping(value="/order_deadline", method=RequestMethod.GET)
	public ApiResponse<String> getDateOrderDeadline() {
    	
    	String orderDeadline=configurationService.getDateOrderDeadline();
    	String currentTime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
    	
  
    	return new ApiResponse<>(HttpStatus.OK.value(),"Date Order Dead line Retrive Successfully ","{\"orderDeadline\" : \""+orderDeadline+"\", \"currentTime\" : \""+currentTime+"\"}");
    	
	}
}

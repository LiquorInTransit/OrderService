package com.gazorpazorp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.LineItem;
import com.gazorpazorp.model.Order;
import com.gazorpazorp.model.dto.OrderMinimalDto;
import com.gazorpazorp.model.dtoMapper.OrderMapper;
import com.gazorpazorp.repository.OrderRepository;
import com.gazorpazorp.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder (ArrayList<LineItem> items) throws Exception {
		return Optional.ofNullable(orderService.createOrder(items))
				.map(o -> new ResponseEntity<Order>(o, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Could not create order!"));
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderMinimalDto>> getAll() throws Exception{
		return Optional.ofNullable(orderService.getAllOrdersForAccount())
				.map(o -> new ResponseEntity<List<OrderMinimalDto>>
						(o.stream()
						.map(order -> OrderMapper.INSTANCE.orderToOrderMinimalDto(order))
						.collect(Collectors.toList()), HttpStatus.OK)
					)
				.orElseThrow(() -> new Exception("Account does not exist"));
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity getOrderById (@PathVariable Long orderId) throws Exception {
		return Optional.ofNullable(orderService.getOrderById(orderId))
				.map(o -> new ResponseEntity<Order>(o, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Account does not exist"));
	}
	
	@GetMapping("/orders/current")
	public ResponseEntity<List<Order>> getCurrentOrder () throws Exception {
		return Optional.ofNullable(orderService.getCurrentOrder())
				.map(o -> new ResponseEntity<List<Order>>(o, HttpStatus.OK))
				.orElseThrow(() -> new Exception("No Current Order"));
	}
	
	@DeleteMapping("/orders/current")
	public ResponseEntity deleteCurrentOrder () throws Exception {
		orderService.deleteCurrentOrder();
		return new ResponseEntity(null, HttpStatus.NO_CONTENT);
	}

	
	
}

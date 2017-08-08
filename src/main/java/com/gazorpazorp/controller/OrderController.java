package com.gazorpazorp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.Order;
import com.gazorpazorp.repository.OrderRepository;
import com.gazorpazorp.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity getOrderById (@PathVariable Long orderId) throws Exception {
		return Optional.ofNullable(orderService.getOrderById(orderId))
				.map(o -> new ResponseEntity<Order>(o, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Account does not exist"));
	}
	
	@GetMapping("/orders")
	public List<Order> getAll() {
		return orderRepository.findAll();
	}
}

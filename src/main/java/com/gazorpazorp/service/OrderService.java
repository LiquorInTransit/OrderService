package com.gazorpazorp.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.AccountClient;
import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.LineItem;
import com.gazorpazorp.model.Order;
import com.gazorpazorp.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	AccountClient accountClient;
	
	
	public List<Order> getAllOrdersForCustomer() {
		Long customerId = accountClient.getCustomer().getId();
		return orderRepository.findByCustomerId(customerId);
	}

	public Order getOrderById(Long orderId) {
		//Get the order
		Order order = orderRepository.findById(orderId).get();
		
		//validate that the accountId of the order belongs to the user
		try {
			validateCustomerId(order.getCustomerId());
		} catch (Exception e) {
			System.out.println("FAILED VALIDATION");
			return null;
		}
		
		return order;
	}
	
	public Order getCurrentOrder() {
		Long accountId = accountClient.getCustomer().getId();
		return orderRepository.findCurrentOrderForCustomer(accountId);
	}
	public boolean deleteCurrentOrder() {
		Long accountId = accountClient.getCustomer().getId();
		Order order = orderRepository.findCurrentOrderForCustomer(accountId);
		if (order == null)
			return false;
		orderRepository.delete(order);
		return true;
	}
	
	
	public Order createOrder (List<LineItem> items) throws Exception {
		Long customerId = accountClient.getCustomer().getId();
		if (orderRepository.findCurrentOrderForCustomer(customerId) != null) {
			throw new Exception ("Customer already has an active order");
		}
		Order order = new Order();
		order.setCustomerId(customerId);
		order.setDeliveryLocation("SOME RANDOM LOCATION");
		order.setStoreLocation("SOME RANDOM LOCATION");
		order.setItems(new HashSet(items));
		order.setTotal(125.25);
		order.setStatus("picking_items");
		order.setOrderDate(new Date());
		return orderRepository.save(order);
	}
	


	private boolean validateCustomerId(Long customerId) throws Exception {
		Customer customer = accountClient.getCustomer();
		
		if (customer != null && customer.getId() != customerId) {
			throw new Exception ("Account number not valid");
		}
		return true;
	}
}

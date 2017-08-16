package com.gazorpazorp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.AccountClient;
import com.gazorpazorp.client.TokenRequestAccountClient;
import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.LineItem;
import com.gazorpazorp.model.Order;
import com.gazorpazorp.model.dto.OrderMinimalDto;
import com.gazorpazorp.model.dtoMapper.OrderMapper;
import com.gazorpazorp.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	AccountClient accountClient;
	
	
	public List<Order> getAllOrdersForAccount() {
		Long accountId = accountClient.getAcct().getId();
		return orderRepository.findByAccountId(accountId);
	}

	public Order getOrderById(Long orderId) {
		//Get the order
		Order order = orderRepository.findById(orderId).get();
		
		//validate that the accountId of the order belongs to the user
		try {
			validateAccountId(order.getAccountId());
		} catch (Exception e) {
			System.out.println("FAILED VALIDATION");
			return null;
		}
		
		return order;
	}
	
	public List<Order> getCurrentOrder() {
		Long accountId = accountClient.getAcct().getId();
		return orderRepository.getCurrentOrderForAccount(accountId);
	}
	public boolean deleteCurrentOrder() {
		Long accountId = accountClient.getAcct().getId();
		List<Order> orders = orderRepository.getCurrentOrderForAccount(accountId);
		if (orders.isEmpty())
			return false;
		orders.forEach(orderRepository::delete);
		return true;
	}
	
	
	public Order createOrder (List<LineItem> items) {
		Order order = new Order();
		order.setAccountId(accountClient.getAcct().getId());
		order.setDeliveryLocation("SOME RANDOM LOCATION");
		order.setStoreLocation("SOME RANDOM LOCATION");
		order.setItems(new HashSet(items));
		order.setTotal(125.25);
		order.setStatus("picking_items");
		return orderRepository.save(order);
	}
	


	private boolean validateAccountId(Long accountId) throws Exception {
		Account account = accountClient.getAcct();
		
		System.out.println(accountId);
		if (account != null && account.getId() != accountId) {
			throw new Exception ("Account number not valid");
		}
		return true;
	}
}

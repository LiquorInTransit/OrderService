package com.gazorpazorp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.AccountClient;
import com.gazorpazorp.client.TokenRequestAccountClient;
import com.gazorpazorp.model.Order;
import com.gazorpazorp.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	AccountClient accountClient;

	@Autowired
	TokenRequestAccountClient tknReqActClient;

	public Order getOrderById(Long orderId) {

		System.out.println("tokClient: " + accountClient.getAcct().toString());

		Order order = orderRepository.findById(orderId).get();
		System.out.println("order: " + order);
		System.out.println("accountClient:" + accountClient);
		//System.out.println(tknReqActClient.getAcct(order.getAccountId()));

		Long accountUserId = Long.parseLong(accountClient.getAcct().get(0).get("userId").toString());//Long.parseLong(tknReqActClient.getAcct(order.getAccountId()).get("userId").toString());
		if (!verifyUser(accountUserId))
			return null;
		System.out.println("Account User Id: " + accountUserId);
		
		
		order = createOrder(null);
		
		
		return order;
	}
	
	public Order createOrder (List<Long> itemIds) {
		Order order;
		//Now we have 2 options for how to create Orders/
		//Option1
		order = createOrderOption1(itemIds);
		//Option2
		order = createOrderOption2(itemIds);
		
		order = orderRepository.save(order);
		return order;
	}
	
	//Option1, get userId here, then get Accounts by UserId (use the first account), then create the order using that id.
	//!!NOTE!! I had to create a whole new endpoint on the account-service to make this option even viable...s
	private Order createOrderOption1(List<Long> itemIds) {
		Long accountId = Long.parseLong(tknReqActClient.getAcctsByUserId(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).get(0).get("id").toString());
		System.out.println("accountId v1: " + accountId);
		Order order = new Order();
		order.setId(new Long(2));
		order.setAccountId(accountId);
		order.setTotal(100);
		return order;
	}
	
	//Option1, get account using forwardTokenAccountClient (use the first account), then create the order using that id.
	private Order createOrderOption2(List<Long> itemIds) {
		Long accountId = Long.parseLong(accountClient.getAcct().get(0).get("id").toString());
		System.out.println("accountId v2: " + accountId);
		Order order = new Order();
		order.setId(new Long(3));
		order.setAccountId(accountId);
		order.setTotal(100);
		return order;
	}

	private boolean verifyUser(Long userId) {
		return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()) == userId;
	}
}

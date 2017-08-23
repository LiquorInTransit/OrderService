package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.gazorpazorp.model.Customer;

@FeignClient(name="account-client")
public interface CustomerClient {
	
	@GetMapping(value="/me", consumes = "application/json")
	Customer getAcct();
}


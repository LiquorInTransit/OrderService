package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.gazorpazorp.model.Customer;

@FeignClient(name="customer-client")
public interface CustomerClient {
	
	@GetMapping(value="/customers/", consumes = "application/json")
	Customer getAcct();
}


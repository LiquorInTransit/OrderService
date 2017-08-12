package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.gazorpazorp.model.Account;

@FeignClient(name="account-client")
public interface AccountClient {
	
	@GetMapping(value="/accounts/", consumes = "application/json")
	Account getAcct();
}


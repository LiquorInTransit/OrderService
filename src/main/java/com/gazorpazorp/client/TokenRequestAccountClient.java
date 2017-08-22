package com.gazorpazorp.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.Customer;

@FeignClient(name="token-account-client", configuration=TokenRequestClientConfiguration.class)
public interface TokenRequestAccountClient {
	
	@GetMapping(value="/accounts/{id}", consumes = "application/json")
	Customer getAcct(@PathVariable("id") Long id);
	
	@GetMapping(value="/accounts/by_user_id/{id}", consumes="application/json")
	List<Customer> getAcctsByUserId(@PathVariable("id") Long id);
}

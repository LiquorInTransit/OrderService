package com.gazorpazorp.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.gazorpazorp.model.Account;

@FeignClient(name="account-client"/*, configuration = DefaultFeignConfiguration.class*/)
public interface AccountClient {
	
	@GetMapping(value="/accounts/", consumes = "application/json")
	List<Account> getAcct();
}


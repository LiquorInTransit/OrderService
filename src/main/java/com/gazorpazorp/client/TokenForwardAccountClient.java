package com.gazorpazorp.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="token-account-client", configuration = TokenForwardConfiguration.class)
public interface TokenForwardAccountClient {
	
	@GetMapping(value="/accounts/", consumes = "application/json")
	List<Map<String, Object>> getAcct();
}

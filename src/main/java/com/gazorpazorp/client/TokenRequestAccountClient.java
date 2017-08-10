package com.gazorpazorp.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="token-account-client", configuration=TokenRequestClientConfiguration.class)
public interface TokenRequestAccountClient {
	
	@GetMapping(value="/accounts/{id}", consumes = "application/json")
	Map<String, Object> getAcct(@PathVariable("id") Long id);
	
	@GetMapping(value="/accounts/by_user_id/{id}", consumes="application/json")
	List<Map<String, Object>> getAcctsByUserId(@PathVariable("id") Long id);
}

package com.gazorpazorp.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="account-client"/*, configuration = DefaultFeignConfiguration.class*/)
public interface AccountClient {
	
	@GetMapping(value="/accounts/", consumes = "application/json")
	List<Map<String, Object>> getAcct();
}


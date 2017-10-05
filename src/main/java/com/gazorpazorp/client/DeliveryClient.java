package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.Delivery;

@FeignClient(name = "delivery-service", configuration = TokenRequestClientConfiguration.class)
public interface DeliveryClient {

	@PostMapping(value = "/internal/deliveries/", consumes = "application/json")
	public ResponseEntity<String> createDelivery (@RequestParam("quoteId")Long quoteId, @RequestParam("orderId")Long orderId);
	
	@GetMapping(value="/internal/deliveries")
	public ResponseEntity<Delivery> getDeliveryByOrderId(@RequestParam("orderId") Long id);
	
	@DeleteMapping(value="/internal/deliveries")
	public ResponseEntity deleteDeliveryByOrderId(@RequestParam("orderId") Long id);
}

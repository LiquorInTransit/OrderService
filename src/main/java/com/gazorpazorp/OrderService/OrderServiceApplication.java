package com.gazorpazorp.OrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.gazorpazorp.client.CustomOAuth2FeignRequestInterceptor;

import feign.RequestInterceptor;

@SpringBootApplication(scanBasePackages="com.gazorpazorp")
@EnableJpaRepositories("com.gazorpazorp.repository")
@EntityScan(basePackages="com.gazorpazorp")
//@EnableEurekaClient
@EnableFeignClients("com.gazorpazorp.client")
@EnableResourceServer
@EnableOAuth2Client
//@EnableHystrix
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	feign.RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext context) {
		if (context == null) return null;
		return new CustomOAuth2FeignRequestInterceptor(context);
	}
	
	//End goal is to move these to a config class, and make the default not require a custom config class
//	@Bean
//	@ConfigurationProperties(prefix = "security.oauth2.client")
//	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
//		return new ClientCredentialsResourceDetails();
//	}	
//	@Bean
//	public RequestInterceptor oauth2FeignRequestInterceptor(){
//		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//	}
//	@Bean
//	public OAuth2RestTemplate clientCredentialsRestTemplate() {
//		return new OAuth2RestTemplate(clientCredentialsResourceDetails());
//	}
	
	
}

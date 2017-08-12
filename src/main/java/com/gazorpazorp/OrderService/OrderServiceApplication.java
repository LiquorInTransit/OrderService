package com.gazorpazorp.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.cloud.client.loadbalancer.RetryLoadBalancerInterceptor;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.gazorpazorp.client.config.CustomOAuth2FeignRequestInterceptor;

@SpringBootApplication(scanBasePackages="com.gazorpazorp")
//@ComponentScan(basePackages="com.gazorpazorp", excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.gazorpazorp.clientConfig.*"))
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
	
	//TODO: RE-IMPLEMENT THIS SO THAT WE DON'T NEED TO HARD CODE THE ACCESS-TOKEN-URI IN THE YAML OAUTH CLIENT CONFIG
//	@Bean
//	UserInfoRestTemplateCustomizer oauth2RestTemplateCustomizer(RetryLoadBalancerInterceptor interceptor) {
//	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//	    interceptors.add(interceptor);
//	    return template -> {
//	        AccessTokenProviderChain accessTokenProviderChain = Stream
//	                .of(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
//	                        new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider())
//	                .peek(tp -> tp.setInterceptors(interceptors))
//	                .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
//	        template.setAccessTokenProvider(accessTokenProviderChain);
//	    };
//	}
}

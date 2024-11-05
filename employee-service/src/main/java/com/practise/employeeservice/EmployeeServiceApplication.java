package com.practise.employeeservice;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {
//
//	@Bean
//	public RestTemplate restTemplate(){
//		return  new RestTemplate();
//	}
//
//	@Bean
//	@LoadBalanced
//	public WebClient.Builder webClient(){
//		HttpClient httpClient= HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
//	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}

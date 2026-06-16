package com.example.employee_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient departmentWebClient(){
        return WebClient.builder()
                .baseUrl("http://department-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public DepartmentClient departmentClient(WebClient departmentWebClient){
        HttpServiceProxyFactory serviceProxyFactory= HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(departmentWebClient)).build();
        return serviceProxyFactory.createClient(DepartmentClient.class);
    }
}

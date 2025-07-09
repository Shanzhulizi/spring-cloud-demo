package com.lm.order.config;

import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    @Bean
    @LoadBalanced//注解式负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}

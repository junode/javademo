package com.spring.product.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.spring.product")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ProductApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    // 负载均衡
    @LoadBalanced
    // 创建bean
    @Bean
    public RestTemplate initRestTemplate(){
        return new RestTemplate();
    }
}

package com.spring.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: fund启动类
 * @author: junode
 * @create: 2020-12-20 15:00
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class FundApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundApplication.class, args);
    }
}

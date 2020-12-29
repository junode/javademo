package com.spring.fund.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: fund启动类
 * @author: junode
 * @create: 2020-12-20 15:00
 **/
@SpringBootApplication(scanBasePackages = "com.spring.fund")
@EnableDiscoveryClient
// 扫描装配OpenFeign接口道Ioc容器中。
@EnableFeignClients(basePackages = "com.spring.fund")
public class FundApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundApplication.class, args);
    }
}

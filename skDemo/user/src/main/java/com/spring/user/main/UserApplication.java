package com.spring.user.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* @Description 用户微服务
* @Author junode
* @Date 2020/12/23 17:33
**/
@SpringBootApplication(scanBasePackages = "com.spring.user")
@EnableDiscoveryClient
public class UserApplication
{
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

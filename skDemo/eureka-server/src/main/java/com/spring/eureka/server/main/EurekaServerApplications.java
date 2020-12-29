package com.spring.eureka.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: 启动类
 * @author: junode
 * @create: 2020-12-19 22:23
 **/

@SpringBootApplication
// 驱动Eureka服务治理中心
@EnableEurekaServer
public class EurekaServerApplications {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplications.class, args);
    }
}
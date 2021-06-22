package com.junode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: springboot项目启动类
 * @author: hitton
 * @create: 2021-02-07 23:33
 **/
@SpringBootApplication
public class SpringBootDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApp.class, args);
    }
}

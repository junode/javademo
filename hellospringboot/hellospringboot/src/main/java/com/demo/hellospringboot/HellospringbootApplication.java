package com.demo.hellospringboot;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo"})
@MapperScan(basePackages = {"com.demo.dao"})
@Slf4j
public class HellospringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellospringbootApplication.class, args);
	}
}

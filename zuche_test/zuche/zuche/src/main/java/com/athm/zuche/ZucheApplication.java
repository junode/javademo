package com.athm.zuche;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.athm.controller","com.athm.service"})
@MapperScan(basePackages = {"com.athm.dao"})
public class ZucheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZucheApplication.class, args);
	}
}

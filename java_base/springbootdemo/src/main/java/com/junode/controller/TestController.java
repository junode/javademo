package com.junode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试访问控制器
 * @author: hitton
 * @create: 2021-02-07 23:41
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/info")
    public String getInfo() {
        return String.format("hello %s","junode");
    }

    @RequestMapping("/name")
    public String getName(String name){
        return name;
    }

    @RequestMapping("/ids/{id}")
    public String getUserId(String id) {
        System.out.println(id);
        return "hello world "+id;
    }

}

package com.spring.product.controller;

import com.spring.product.facade.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.common.model.ResultMessage;

/**
 * @Description 测试熔断器
 * @Author junode
 * @Date 2020/12/26
 */
@RestController
public class CircuitBreakerController {
    @Autowired
    private IUser user;

    @RequestMapping("/cr/timeout")
    public ResultMessage timeout(){
        return user.timeout();
    }

    @RequestMapping("cr/exp/{msg}")
    public ResultMessage exp(@PathVariable("msg") String msg){
        return user.exp(msg);
    }
}

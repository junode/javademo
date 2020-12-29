package com.spring.fund.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.common.model.ResultMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 交易流程-实现账户扣减
 * @author: junode
 * @create: 2020-12-20 22:17
 **/
@RestController
@RequestMapping("/fund")
public class AccountController {

    // 账户资金扣减
    @PostMapping("/account/balance/{userId}/{amount}")
    public ResultMessage deductingBalance(@PathVariable("userId") Long userId,
                                          @PathVariable("amount") Double amount,
                                          HttpServletRequest request){
        // 打印当前服务端口用于检测
        String message = "端口：【"+request.getServerPort() +"】扣减成功";
        ResultMessage resultMessage = new ResultMessage(true,message);
        return resultMessage;
    }
}

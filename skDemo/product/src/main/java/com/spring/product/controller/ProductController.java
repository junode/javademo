package com.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.cloud.common.model.ResultMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户购买理财产品入口
 * @author: junode
 * @create: 2020-12-20 22:40
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    // 依赖注入RestTemplate
    @Autowired
    private RestTemplate template;
    // http://localhost:3001/product/purchase/1/1/1000
    @GetMapping("/purchase/{userId}/{productId}/{amount}")
    public ResultMessage purchaseProduct(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId,
            @PathVariable("amount") Double amount){
        System.out.println("扣减成功");
        // 使用FUND代表资金微服务,RestTemplate会自动负载均衡
        String url = "http://FUND/fund/account/balance/{userId}/{amount}";
        // 封装请求参数
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("amount",amount);
        // 请求资金微服务
        ResultMessage resultMessage = template.postForObject(url,null,ResultMessage.class,params);
        // 打印资金微服务返回的消息
        System.out.println(resultMessage.getMessage());
        System.out.println("记录交易信息");
        return new ResultMessage(true,"交易成功");
    }
}

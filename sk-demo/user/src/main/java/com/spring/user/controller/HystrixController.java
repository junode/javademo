package com.spring.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.common.model.ResultMessage;

/**
 * @Description 定义用户模块的服务提供者
 * 使用用户微服务作为产品微服务的服务提供者，以测试服务消费者（即产品微服务）的熔断器是否生效。
 *
 * 当用户微服务发生异常时，产品微服务会启动熔断器，减少与用户微服务交互，防止自己被用户微服务带崩了。
 * @Author junode
 * @Date 2020/12/26
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    // 最大休眠时间
    private static Long MAX_SLEEP_TIME = 5000L;

    /**
     * 随机超时测试，触发无误消费者启用断路器
     * @return
     */
    @RequestMapping("/timeout")
    public ResultMessage timeout(){
        // 随机产生一个小于5000的长整型随机数
        Long sleepTime = (long)(MAX_SLEEP_TIME * Math.random());
        // 线程按一个随机数字休眠，使得服务消费者能够存在一定的概率产生熔断
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("执行异常"+e.getMessage());
        }
        return new ResultMessage(true,"执行时间"+sleepTime);
    }

    /**
     * 异常测试，触发服务消费者启用断路
     * @param msg
     * @return
     */
    @RequestMapping("/exp/{msg}")
    public ResultMessage exp(@PathVariable("msg") String msg){
        if("spring".equals(msg)){
            return new ResultMessage(true,msg);
        }else{
            throw new RuntimeException("出现了异常");
        }
    }
}

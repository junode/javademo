package com.spring.product.facade.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.product.facade.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.cloud.common.model.ResultMessage;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Description TODO
 * @Author junode
 * @Date 2020/12/26
 */
@Service
public class UserImpl implements IUser {

    // 注入RestTemplate，在Ribbon中我们标注了@LoadBalance，用以实现负载均衡
    @Autowired
    private RestTemplate template;


    @Override
    // @HystrixCommand将方法退出Hystrix进行监控
    // 配置项fallbackMethod指定了降级服务的方法
    @HystrixCommand(fallbackMethod = "fallback1")
    public ResultMessage timeout() {
        String url="http://USER/hystrix/timeout";

        return template.getForObject(url,ResultMessage.class);
    }

    // 只要超时了就降级调用该方法
    // 降级方法1
    public ResultMessage fallback1(){
        return new ResultMessage(false,"超时了");
    }

    /**
     * 若出现了异常，则调用降级方法2返回请求结果
     * 降级方法2，带有参数
     * @param msg msg -- 消息
     * @return
     */
    public ResultMessage fallback2(String msg){
        return new ResultMessage(false,"调用产生异常了，msg"+msg);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback2")
    public ResultMessage exp(String msg) {
        String url = "http://USER/hystrix/exp/{msg}";
        return template.getForObject(url,ResultMessage.class,msg);
    }

    @Override
    public ResultMessage timeout2() {
        return null;
    }

    @Override
    public Future<ResultMessage> asyncTimeout() {
        return null;
    }

    @Override
    public List<ResultMessage> exp2(String[] params) {
        return null;
    }
}

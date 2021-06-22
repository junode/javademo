package com.spring.config;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 自定义全局Ribbon组件
 * @Author junode
 * @Date 2020/12/24
 */
// 这是一个不能被程序入口扫描的配置类，否则它对所有的Ribbton策略生效

// name配置具体的客户端，configuration指向一个具体的配置类FundConfiguration，
// 测试要求FundConfiguration标注@Configuration
@RibbonClient(name = "FUND", configuration = FundConfiguration.class)
@Configuration
public class GlobalConfig {

    /**
     * @Description 在这边配置的信息，比yml文件配置信息的优先级更高。若想测试时yml文件中相关配置生效，则需要屏蔽这边的代码
     * ribbton还提供了@RibbonClient和@RibbontClients注解，这两个注解的优先级没有该配置文件和yml的相关配置高。
     * @Author junode
     * @Date 2020/12/24 17:59
     **/
    // Bean name 要与ribbon提供的一致
//    @Bean(name="ribbonServerListFilter")
//    public ServerListFilter<Server> serverListFilter(){
//        // 使用优先选择的过滤器
//        ZonePreferenceServerListFilter filter = new ZonePreferenceServerListFilter();
//        // 使用默认zone
//        filter.setZone(EndpointUtils.DEFAULT_ZONE);
//        return filter;
//    }
//
//    @Bean
//    public IRule rule(){
//        // 使用随机选择的服务策略
//        return new RandomRule();
//    }
}

package org.example.ioc.configuration;

import org.example.ioc.domain.Item;
import org.example.ioc.domain.Store;
import org.example.ioc.dependency.injection.ItemImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Constructor-Based Dependency Injection
 * @Author junode
 * @Date 2021/3/7
 */
@Configuration
//@ComponentScan("") 是否需要指定扫描范围？
public class AppConfig {

    @Bean
    public Item item1() {
        return new ItemImpl();
    }

    @Bean(name = "store3")
    public Store store() {
        System.out.println("test");
        return new Store(item1(),"test");
    }
}

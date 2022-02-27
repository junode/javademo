package org.example.ioc.dependency.container;

import org.example.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @description: ApplicationContext作为IoC容器
 * @author: hitton
 * @create: 2021-03-10 00:17
 **/
@Configuration
public class ApplicationContextIoCContainer {
    public static void main(String[] args) {
        // 创建ApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 以当前类作为配置类加入applicationContext
        applicationContext.register(ApplicationContextIoCContainer.class);
        // 不刷新则报错，启动容器
        applicationContext.refresh();
        lookupByCollectionType(applicationContext);
    }

    @Bean
    public User user(){
        User demo = new User();
        demo.setId("1L");
        demo.setName("junode");
        return demo;
    }

    // 根据类型查找 集合类型
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查询找集合："+userMap);
        }
    }
}

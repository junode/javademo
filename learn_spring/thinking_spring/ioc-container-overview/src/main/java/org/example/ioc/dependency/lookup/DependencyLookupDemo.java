package org.example.ioc.dependency.lookup;

import org.example.ioc.annotation.Super;
import org.example.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @description: 依赖查找示例
 * 1 通过名称方式进行查找
 * @author: hitton
 * @create: 2021-03-03 23:25
 **/
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // 配置 xml 文件
        // 启动 Spring 容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INFO/dependency-lookup-context.xml");
        lookUpByAnnotation(beanFactory);// 在指定注解查找时，list中的bean将被过滤
        lookupByCollectionType(beanFactory);
//        lookupByType(beanFactory);
        // 延迟加载
//        lookupInLazy(beanFactory);
        // 实时查找
//        lookupInRealTime(beanFactory);
    }

    // 根据注解找到bean
    private static void lookUpByAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(users);
        }
    }

    // 根据类型查找 集合类型
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查询找集合："+userMap);

        }
    }
    /**
     * 根据类型查找 单类型
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("类型查找之实时查找： "+user);
    }

    // 依赖查找 -- 延迟查找
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找："+user);
    }
    // 依赖查找 -- 实时查找
    private static void lookupInRealTime(BeanFactory beanFactory){
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找："+user);
    }
}

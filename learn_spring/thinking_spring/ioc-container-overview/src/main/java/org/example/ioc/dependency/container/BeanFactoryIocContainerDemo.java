package org.example.ioc.dependency.container;

import org.example.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @description: BeanFactory作为IoC容器启动
 * @author: hitton
 * @create: 2021-03-10 00:09
 **/
public class BeanFactoryIocContainerDemo {
    public static void main(String[] args) {
        // 创建Bean Factory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 通过XML方式配置beanFactory
        String location = "classpath:META-INFO/dependency-lookup.xml";
        // 加载配置
        int beans = reader.loadBeanDefinitions(location);
        System.out.println("加载bean的数量为："+beans);

        lookupByCollectionType(beanFactory);
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

package org.example.ioc.dependency.injection;

import org.example.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanFactory和ApplicationContext谁才是真正的IoC容器？
 * 承接于DependencyInjectionDemo。
 * @Author junode
 * @Date 2021/3/7
 */
public class WhoIsRealIocContainer {
    public static void main(String[] args) {
        /**
         * 根据我们new的对象可知，我们是通过XML文件来初始化ApplicationContext对象，但我们用的是BeanFactory进行接收
         * 根据查看继承关系可知，表明了ApplicationContext是BeanFactory的子接口。
         */
        // BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INFO/dependency-injection-context.xml");
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath:META-INFO/dependency-injection-context.xml");




        UserRepository repository = (UserRepository) applicationContext.getBean("userRepository");

        // 为什们这个表达式不成立呢？1 ApplicationContext就是BeanFactory，ApplicationContext继承BeanFactory
        // 2 ApplicationContext和BeanFactory本身就是两个对象
        System.out.println(applicationContext == repository.getBeanFactory());// todo repository.getBeanFactory()怎么来的呢？
    }
}

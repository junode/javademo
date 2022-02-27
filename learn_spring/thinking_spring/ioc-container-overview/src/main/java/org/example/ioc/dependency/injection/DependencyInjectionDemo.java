package org.example.ioc.dependency.injection;

import org.example.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;


/**
 * 依赖注入demo
 * 依赖注入与依赖查找的来源并不是同一个。
 * @Author junode
 * @Date 2021/3/7
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {

        // 配置 XML 文件
        // 启动Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INFO/dependency-injection-context.xml");
        UserRepository repository = (UserRepository) beanFactory.getBean("userRepository");

        // 依赖来源1 -- 自定义bean对象
        System.out.println(repository.getUsers());

        // 依赖来源2 -- 容器依赖注入（内建依赖）
        System.out.println(repository.getBeanFactory()); // 依赖注入 内建非bean对象
        System.out.println(beanFactory == repository.getBeanFactory());// todo repository.getBeanFactory()怎么来的呢？
        /**
         * 我们 repository.getBeanFactory() 可以获得一个BeanFactory对象，而我萌儿通过beanFactory.getBean(BeanFacotry.class)却
         * 找不到BeanFactory对象。那我们的repository.getBeanFactory()的BeanFactory对象是从哪里来的呢？
         */
        // System.out.println(beanFactory.getBean(BeanFactory.class)); // 依赖查找 报错

        // 依赖来源3 -- 容器内建 bean对象
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的容器内建bean对象 " + environment);

        //
        System.out.println("============ 注入 ApplicationContext对象 start ==========");
        System.out.println(repository.getApplicationContextObjectFactory());
        System.out.println(repository.getApplicationContextObjectFactory().getObject());
        // todo 此时 beanFactory 与 ApplicationContext相等呢？
        System.out.println(repository.getApplicationContextObjectFactory().getObject()== beanFactory);
        System.out.println("============ 注入 ApplicationContext对象 end ==========");

        System.out.println("============ 注入 User对象 start ==========");
        System.out.println(repository.getUserObjectFactory());
        System.out.println(repository.getUserObjectFactory().getObject()); // 因为superUser用primary指定了，所以优先找到superUser把。
        System.out.println(repository.getUserObjectFactory() == beanFactory);
        System.out.println("============ 注入 User对象 end ==========");
    }
}

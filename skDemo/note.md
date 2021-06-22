# 1 学习笔记
``
练习来源：《Spring cloud 微服务和分布式系统实践》杨开振 
``
## 1.1 eureka认识
``
文章参考：Spring Cloud Eureka详解 
https://blog.csdn.net/sunhuiliang85/article/details/76222517
``



eureka.client.service-url.defaultZone : http://localhost:5001/eureka
表示向 http://localhost:5001/eureka 注册服务。

eureka.instance.hostname 注册服务实例设置名称

## 1.2 eureka 源码初步认识

### 1.2.1 关于注解的认识：https://www.javatpoint.com/java-annotation

+ 关于注解的内容移到了wps的云文档。

  
## 1.2 Feign的认识

### 1.2.1 Feign入门实例
#### 1.2.1.1 FUND微服务下IUser类说明

+ @FeignClient("user"): 表示这个接口是一个OpenFeign的客户端，底层将使用Ribbon执行REST风格调用，
  配置的“user"是一个微服务的名称，指向用户微服务，也是准备调用的是用户微服务。
  
+ @GetMapping("/user/info/{id}")：GetMapping表示使用HTTP的GET请求调用用户微服务，而”/user/info/{id}"
则表示URI，其中“{id}"表示参数占位，而”/user/info/{id}"则表示URI，其中的“{id}"表示参数占位，因此方法中的参数
  也使用@PathVariable("id")修改参数id，来和这个占位对应。
  
+ @PutMapping("/user/info")：PutMapping表示使用HTTP的PUT请求调用用户微服务，而”/user/info"则表示URI，对于
这个请求，需要一个JSON的请求体，因此参数使用@RequestBoby修饰UserInfo类型的参数，代表将参数转换为JSON请求体，
  对该URI进行请求。


#### 1.2.1.2 FUND微服务下启动器添加的@EnableFeignClients(basePackages = "com.spring.fund")注解说明
+ @EnableFeignClients用于驱动OpenFeign工作并指定扫描包， 对带有注解@OpenFeign的接口进行扫描，
  并将他们装配到Ioc容器中。从而可以用Spring的工作环境中使用该接口进行声明式调用了。
  

# 2 JAVA VALIDATION学习 JSR380

参照：

+ https://docs.oracle.com/javaee/7/tutorial/bean-validation-advanced001.htm        
+ https://in.relation.to/2017/03/02/adding-custom-constraint-definitions-via-the-java-service-loader/
+ https://www.baeldung.com/javax-validation
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

eureka.instance.hostname在客户端配置了没任何作用吧？应该是给 注册服务实例设置名称

## 1.2 eureka 源码初步认识

### 1.2.1 java.lang.annotation.Document的理解
&ensp;&ensp;关于该注解的实例可以参考PracDocument.java。

&ensp;&ensp;网上说明：Java提供的Documented元注释跟Javadoc的作用是差不多的，其实它存在的好处是开发人员可以定制
Javadoc不支持的文档属性，并在开发中应用。(就是@Document注解的注释内容会被当成注释记录再java doc中)。

&ensp;&ensp;网上说明：@Document的作用：<b>代码注释文档化（javadoc），即：归档时，注释也编译</b>

### 1.2.2 java.lang.annotation.Inherited的理解：
&ensp;&ensp;网上解释：目的：注释自动继承父类，直至Object为止。注意：从超类继承，而不是接口，即使实现接口，也不继承。



                


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

### 1.2.1 关于注解的认识：https://www.javatpoint.com/java-annotation

注解是表示元数据的标签，可以用于修饰类、接口、方法或字段，为他们添加元数据信息，可以作为他们的额外信息用于java编译和jvm使用。

#### 1.2.1.1 java.lang.annotation.Document的理解
&ensp;&ensp;关于该注解的实例可以参考PracDocument.java。

&ensp;&ensp;网上说明：Java提供的Documented元注释跟Javadoc的作用是差不多的，其实它存在的好处是开发人员可以定制
Javadoc不支持的文档属性，并在开发中应用。(就是@Document注解的注释内容会被当成注释记录再java doc中)。

&ensp;&ensp;网上说明：@Document的作用：<b>代码注释文档化（javadoc），即：归档时，注释也编译</b>

#### 1.2.1.2 java.lang.annotation.Inherited的理解：
&ensp;&ensp;网上解释：目的：注解自动继承父类，直至Object为止。注意：从超类继承，而不是接口，即使实现接口，也不继承。


#### 1.2.1.3 java.lang.annotaion.SuppressWarnings的理解：

@SuppressWarnings annotation: is used to suppress warnings issued by the compiler.

#### 1.2.1.4 java.lang.annotaion.Target的理解：
参考：https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/annotation/Target.html

- 表明了被@Target注解修饰的注解适用的上下文。被Target修饰的注解可能是需要java编译器或JVM实现特殊需求。
关于@Target注解的取值可以查看java.lang.annotation.ElementType类，该类的取值限定了
注解只能修饰的范围（既注解使用的上下文被限定了）。
- 如：@Target(ElementType.TYPE) 被该注解修饰的注解，只能用于修饰类、接口或ENUM对象。可查看Element.TYPE的说明。

#### 1.2.1.5 java.lang.annotation.Retention的理解

表示带注释类型的注释将保留多长时间。如果注释类型声明上没有Retion注释，则保留策略默认为RetentionPolicy.CLASS。

仅当元注解类型直接用于注解时，Rention元注解才有效。如果将元注解类型用作其他注解类型的成员类型，则无效。

RentionsPolicy类有三个枚举类型取值：
- SOURCE ：Annotations are to be discarded by the compiler.
- CLASS : Annotations are to be recorded in the class file by the compiler but need not be retained by the VM at run time. 
            This is the default behavior.
- RUNTIME : Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, 
            so they may be read reflectively.
简而言之：SOURCE:再编译时注解就被清除；CLASS : 再JVM运行时该注解被清除；RUNTIME ：JVM运行时也存在，相当于一直存货，生命周期与JVM一样吧。




                


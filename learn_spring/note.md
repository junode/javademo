# 1 Java Agent的粗浅认识

实例参考person_prac下com.javaAgent练习
参考来源：https://www.cnblogs.com/rickiyang/p/11368932.html
中文名：《Java Agent使用指南》

## 1.1 JVM启动前静态Instrument#
### 1.1.1 Java Agent 是什么？

Javaagent是java命令的一个参数。参数 javaagent 可以用于指定一个 jar 包，并且对该 java 包有2个要求：

这个 jar 包的 MANIFEST.MF 文件必须指定 Premain-Class 项。

+ Premain-Class 指定的那个类必须实现 premain() 方法。
    
+ premain 方法，从字面上理解，就是运行在 main 函数之前的的类。当Java 虚拟机启动时，在执行 main 函数之前，
    JVM 会先运行-javaagent所指定 jar 包内 Premain-Class 这个类的 premain 方法 。

在命令行输入 java可以看到相应的参数，其中有 和 java agent相关的：

    -agentlib:<libname>[=<options>]
                  load native agent library <libname>, e.g. -agentlib:jdwp
                  see also -agentlib:jdwp=help
    -agentpath:<pathname>[=<options>]
                  load native agent library by full pathname
    -javaagent:<jarpath>[=<options>]
                  load Java programming language agent, see java.lang.instrument

再Java 11版本中，java.lang.instrument是在java.instrument的jar包下。
对于java.lang.instrument包的下作简单的认识：
+ ClassDefinition : 按照该类名，该类是作用的是对类重新定义。该类重给定了两个属性，一个是要被重新定义类的属性；
一个是要用于替代类的字节数组属性。该类在Instrumentation.redefineClasses方法中使用。

+ ClassFileTransformer ： 类文件转换器。当类被loaded，redefined或retransformed时，为了让
transformer's的transform方法被调用，An agent使用addTransformer方法注册ClassFileTransformer接口的实现类。
该实现类需要重写ClassFileTransformer接口定义的transform方法。在JVM定义类之前被调用转换器。


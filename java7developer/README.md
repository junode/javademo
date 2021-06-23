索引文件：索引文件是销售商与TA交互时的发送的文件，用于告诉TA你将接收到哪些文件，按照索引文件进行解析即可。

代码来源：Java程序员修炼之道

Java7的变化主要包括两部分：Coin项目和NIO.2

Coin项目：

    try-with-resources结构（可以自动关闭资源）
    switch中的字符串
    对数字常量的改进
    Multi-catch（在一个catch块中声明多个要捕获的异常）
    钻石语法（在处理泛型时不用那么反锁了）

NIO.2
    
    没看懂。。。
    
## 第一章 初识JAVA7
Java7中的新特性
- switch语句中的String
- 数字常量的新形式

    - 数字常量可以使用二进制文本表示
    - 在整型常量中可以使用下划线来提高可读性
    
- 改进的异常处理
    - multicatch
    - final重抛
- try-with-resources（TWR）
    + 要确保try-with-resources生效，则要为每一个资源声明独立的变量
- 钻石语法
    + 解决<b>创建泛型定义和和实例太过繁琐问题</b>
    + Map<Integer,Map<String,String>> uls = new HashMap<Integer,Map<String,String>>();
    + Map<Integer,Map<String,String>> uls = new HashMap();
- 变参警告位置修改

## 第二章 NIO.2
### 2.1 JAVA I/O概述
测试
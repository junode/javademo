# 笔记来源于汪文君的视频

# 第一节 Joiner

## What is Joiner?
> Joiner, which is designed to simplify the process of joining together multiple strings with a delimiter.

这句话告诉了Joiner是什么，以及有什么功能，还有就是什么场景使用Joiner。
- 是什么：Joiner是采用特定限定符将多个字符串拼接起来的类。
- 功能：采用特定限定符将多个字符串拼接
- 场景：当你需要字符串拼接时。

## Joiner use case(test see JoinerTest.java)
- Joining elements
- handling null values with default value
- skip null values
- appending to StringBuilder or StringBuffer
- Joining map entries
- 
作者也用Java8去实现了去重和用default value替代null的功能。建议若无必要，无需使用guava。

# 第二节 Splitter
## What is Splitter
> Splitter is designed to split a single input string into collection of substring based on a specified delimiter or pattern.

- 是什么：Splitter是用于特定分隔符/模式将字符串拆分成集合的类。
- 功能：用于特定分隔符/模式将字符串拆分
- 场景：需要将字符串按照一定规则拆分时。
## Splitter use case
- basic splitting
- handling whitespace
- omitting empty string
- limiting the number of substrings
- splitting using regular expressions

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.0/maven-plugin/reference/html/#build-image)


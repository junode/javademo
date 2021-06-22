package com.demo.slf4jTest;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Auther: zwy
 * @Date: 2020/6/19
 * @Description: here is practice for slf4j. the article as follow compares with three frameworks to see where logging
 * framework performances well.
 * @version: origin : Benchmarking Java logging frameworks:https://www.loggly.com/blog/benchmarking-java-logging-frameworks/
 * <p>
 * 补充，这里说一下自己关于日志框架的理解：来源：https://en.wikipedia.org/wiki/Java_logging_framework
 * 日志框架主要分为三部分：1 Logging 2 Formatter 3 Handler(or Appender)
 * 1 关于Logging的认识：logging主要是获取日志信息与相关的元数据信息，将这些信息发送给logging framework
 * 2 logging framework 拿到日志信息与相关元数据后，将信息转发给Formatter，进行格式化处理
 * 3 Formatter格式处理后，将信息转交给合适的Halnder/Appender进行处理，如输出到控制台，存入disk，或者存入database。
 * <p>
 * 同时，在第一篇文章中提及到，因数据传输方式的不同，如TCP/UDP，不同的数据传输方式时间上有差异，且可能会掉数据的情况，具体可看
 * 最上面提及的compare文章。
 */
public class demo01 {

    private static Logger logger = LoggerFactory.getLogger(demo01.class);

    @Test
    public void test1() {
//        PropertyConfigurator.configure("log4j.properties");
        logger.info("hello {}", "world");
        System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation());
    }

}

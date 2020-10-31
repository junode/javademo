package com.demo.io_jdbc;

import com.alipay.rdf.file.interfaces.FileFactory;
import com.alipay.rdf.file.interfaces.FileReader;
import com.alipay.rdf.file.model.FileConfig;
import com.alipay.rdf.file.model.StorageConfig;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zwy
 * @Date: 2020/6/30
 * @Description: 阿里rdf-file熟悉 读文件之简单读写
 */
public class rdfFilePrac {


    /**
    * 功能描述: 来源：https://github.com/alipay/rdf-file/wiki/%E8%AF%BB%E6%96%87%E4%BB%B6%E4%B9%8B%E7%AE%80%E5%8D%95%E8%AF%BB%E5%8F%96
     * 1 读取数据都会转成HashMap实例
     * 2 HashMap的key是数据定义模板中的key。
    * @auther: zwy
    */
    @Test
    public void test1(){
//        String filePath = File.class.getResource("/rdf-tem01.txt").getPath();
//        System.out.println(filePath);
        String filePath = "/E:/idea/java_base/learn_io/target/classes/rdf-tem01.txt";

        FileConfig config = new FileConfig(filePath, "/template01.json", new StorageConfig("nas"));
        FileReader fileReader = FileFactory.createReader(config);
        try {
            Map<String, Object> tail = fileReader.readTail(HashMap.class);
            System.out.println(tail.keySet() + " " + tail.values());
            Map<String, Object> head = fileReader.readHead(HashMap.class);
            System.out.println(head.keySet() + " " + head.values());
            Map<String, Object> row = null;
            while (null != (row = fileReader.readRow(HashMap.class))) {
                // 处理业务
                System.out.println(row);
            }
        } finally {
            fileReader.close();
        }
    }
}

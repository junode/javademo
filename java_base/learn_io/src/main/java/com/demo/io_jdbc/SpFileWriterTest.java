package com.demo.io_jdbc;

import com.alipay.rdf.file.exception.RdfErrorEnum;
import com.alipay.rdf.file.exception.RdfFileException;
import com.alipay.rdf.file.interfaces.FileFactory;
import com.alipay.rdf.file.interfaces.FileWriter;
import com.alipay.rdf.file.model.FileConfig;
import com.alipay.rdf.file.model.FileDefaultConfig;
import com.alipay.rdf.file.model.StorageConfig;
import com.demo.io_jdbc.util.DateUtil;
import com.demo.io_jdbc.util.TemporaryFolderUtil;
import com.demo.io_jdbc.util.TestLog;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zwy
 * @Date: 2020/6/30
 * @Description: rdf-file writer demo
 */
public class SpFileWriterTest {
    TemporaryFolderUtil tf = new TemporaryFolderUtil();

    @Before
    public void setUp() throws IOException{
        tf.create();
        new FileDefaultConfig().setCommonLog(new TestLog());
    }

    @Test
    public void testWriter() throws Exception {
        String filePath = tf.getRoot().getAbsolutePath();
        System.out.println(filePath);
//        String templatePath = "/E:/idea/java_base/learn_io/target/classes/writer/template/sp.json";
        FileConfig config = new FileConfig(new File(filePath, "junodehelloworld.txt").getAbsolutePath(),
                "/sp.json", new StorageConfig("nas"));
        config.setLineBreak("\r");
        config.setFileEncoding("UTF-8");
        writeAndValide(config);

        config.setLineBreak("\n");
        writeAndValide(config);

        config.setLineBreak("\r\n");
        writeAndValide(config);

        try {
            config.setLineBreak("\\c");
            writeAndValide(config);
            Assert.fail();
        } catch (RdfFileException e) {
            Assert.assertEquals(RdfErrorEnum.UNSUPPORT_LINEBREAK, e.getErrorEnum());
        }
    }

    private void writeAndValide(FileConfig config) throws Exception {
        FileWriter fileWriter = FileFactory.createWriter(config);

        Map<String, Object> head = new HashMap<String, Object>();
        head.put("totalCount", 2);
        head.put("totalAmount", new BigDecimal("23.22"));
        fileWriter.writeHead(head);

        Map<String, Object> body = new HashMap<String, Object>();

        Date testDate = DateUtil.parse("2017-01-03 12:22:33", "yyyy-MM-dd HH:mm:ss");

        body.put("seq", "seq12345");
        body.put("instSeq", "303");
        body.put("gmtApply", testDate);
        body.put("date", testDate);
        body.put("dateTime", testDate);
        body.put("applyNumber", 12);
        body.put("amount", new BigDecimal("1.22"));
        body.put("age", new Integer(33));
        body.put("longN", new Long(33));
        body.put("bol", true);
        body.put("memo", "memo1");
        fileWriter.writeRow(body);

        testDate = DateUtil.parse("2016-02-03 12:22:33", "yyyy-MM-dd HH:mm:ss");

        body.put("seq", "seq234567");
        body.put("instSeq", "505");
        body.put("gmtApply", testDate);
        body.put("date", testDate);
        body.put("dateTime", testDate);
        body.put("applyNumber", 12);
        body.put("amount", new BigDecimal("1.09"));
        body.put("age", 66);
        body.put("longN", 125);
        body.put("bol", false);
        body.put("memo", "memo2");
        fileWriter.writeRow(body);

        fileWriter.close();

        //校验文件
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(config.getFilePath())), "UTF-8"));
        Assert.assertEquals("2|23.22", reader.readLine());
        Assert.assertEquals(
                "seq12345|303|2017-01-03 12:22:33|20170103|20170103 12:22:33|12|1.22|33|33|true|memo1",
                reader.readLine());
        Assert.assertEquals(
                "seq234567|505|2016-02-03 12:22:33|20160203|20160203 12:22:33|12|1.09|66|125|false|memo2",
                reader.readLine());

        reader.close();
    }



    @Test
    public void testRequired() throws Exception {
        String filePath = tf.getRoot().getAbsolutePath();

        FileConfig config = new FileConfig(new File(filePath, "demojunode.txt").getAbsolutePath(),
                "/sp.json", new StorageConfig("nas"));
        FileWriter fileWriter = FileFactory.createWriter(config);

        Map<String, Object> head = new HashMap<String, Object>();
        head.put("totalCount", 2);

        try {
            fileWriter.writeHead(head);
            Assert.fail();
        } catch (RdfFileException e) {
            Assert.assertEquals(RdfErrorEnum.VALIDATE_ERROR, e.getErrorEnum());
        }

        Map<String, Object> body = new HashMap<String, Object>();

        Date testDate = DateUtil.parse("2017-01-03 12:22:33", "yyyy-MM-dd HH:mm:ss");

        body.put("seq", "seq12345");
        body.put("gmtApply", testDate);
        body.put("date", testDate);
        body.put("dateTime", testDate);
        body.put("applyNumber", 12);
        body.put("amount", new BigDecimal("1.22"));
        body.put("age", new Integer(33));
        body.put("longN", new Long(33));
        body.put("bol", true);
        body.put("memo", "memo1");

        try {
            fileWriter.writeRow(body);
//            Assert.fail();
        } catch (RdfFileException e) {
            Assert.assertEquals(RdfErrorEnum.VALIDATE_ERROR, e.getErrorEnum());
        } finally {
            fileWriter.close();
        }
    }

    @After
    public void after() {
        tf.delete();
    }

}

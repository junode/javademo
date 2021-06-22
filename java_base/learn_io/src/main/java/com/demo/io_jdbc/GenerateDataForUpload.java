package com.demo.io_jdbc;

import com.demo.io_jdbc.entity.SqlType;
import com.demo.io_jdbc.entity.SqlData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: zwy
 * @Date: 2020/6/10
 * @Description: 用于生成上传的数据
 * @version:
 */
public class GenerateDataForUpload {
    private Integer DOUBLE_DECIMAL = 2;// double数值型的精度

    private Integer ID = 0;
    private Integer host_cust_no = 1000000;
    private Integer cust_no = 10000000;

    /**
     * 1 定义一个实体类：数据量大小；定义一个map，key为enum类型，表示字段类型；value Integer,表示字段长度；定义分隔符
     */

    /**
     * 功能描述: 开始生成数据,写入文本
     * @auther: zwy
     */
    @Test
    public void generateData() {
        // 首先定义一个map,用于表示数据表的大小
        SqlData sqlData = new SqlData();
        sqlData.setRowNum(1000000);// 100w数据
        sqlData.setSplit_label("|");
        sqlData.setDouble_decimal(2);
        sqlData.setId(true);
        sqlData.setType(0);

        List<String> list = new ArrayList<>();
        list.add("VARCHAR_10");
        list.add("INT_2");
        sqlData.setColumns(list);
        // 这里以user_info表为示例，生成数据 VARCHAR,DATE,DOUBLE,INT
        String path = "D:/kayak/generateData.txt";
        saveDataToDisk(sqlData, path,false,Arrays.asList());
    }

    /**
    * 功能描述: 生成客户标签数据
    * @auther: zwy
    */
    @Test
    public void generateData2() {
        // 定义一个map，用于表示数据表的大小。
        SqlData sqlData = new SqlData();
        sqlData.setRowNum(2000000);
        sqlData.setSplit_label("|");
        sqlData.setDouble_decimal(2);
        sqlData.setId(false);

        List<String> list = new ArrayList<>();
        list.add("INT_20");list.add("INT_20");
        list.add("INT_1");list.add("INT_5");
        list.add("INT_7");list.add("INT_1");
        list.add("INT_6");list.add("DATE_0");
        sqlData.setColumns(list);
        String path = "D:/kayak/genClientLabel.txt";
        saveDataToDisk(sqlData, path,false,Arrays.asList());
    }

    /**
    * 功能描述: 生成黑白名单数据
    * @auther: zwy
    */
    @Test
    public void test3(){
        SqlData sqlData = new SqlData();
        sqlData.setRowNum(2000000);
        sqlData.setSplit_label(",");
        sqlData.setDouble_decimal(2);
        List<String> list = new ArrayList<>();
        sqlData.setId(true);
        sqlData.setType(0);
//        list.add("INT_6"); // 名单编号
//        list.add("INT_1"); // 账号类型
        list.add("INT_6"); // 账号
        list.add("VARCHAR_3"); // 客户名称
//        list.add("INT_3"); // 证件类型
        list.add("INT_6");  // 证件号码
//        list.add("INT_7"); // 核心客户好
        list.add("INT_6"); // 手机号
        list.add("DOUBLE_4"); // 金额
//        list.add("INT_6"); // 核心客户号 cust_no
        sqlData.setColumns(list);
        String path = "D:/kayak/blackAndWihte.csv";

        // 添加常量值
        boolean isConstant = true;
        List<String> constantStr = Arrays.asList("00000019");

        saveDataToDisk(sqlData, path,isConstant,constantStr);
    }

    /**
     * 功能描述: 将数据保存到disk
     *
     * @auther: zwy
     */
    public void saveDataToDisk(SqlData sqlData, String path,boolean hasConstant,List<String> constantValue) {
        Integer rows = sqlData.getRowNum();
        List<String> list = sqlData.getColumns();
        String splitLabel = sqlData.getSplit_label();

        // 证件类型
        List<Character> characters = Arrays.asList('1', '2', '3', '4', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

        Integer doubleDecimal = sqlData.getDouble_decimal();
        this.DOUBLE_DECIMAL = doubleDecimal;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(path, true); // 只能写默认编码的文本文件, 以追加的方式写文件
            bufferedWriter = new BufferedWriter(fileWriter);

            int id = 0;
            for (int i = 0; i < rows; i++) {
                StringBuilder sb = new StringBuilder();

                // 是否生成id
                if (sqlData.getId()) {
                    id = generateID(sqlData.getType());
                    sb.append(id);
                    sb.append(splitLabel);
                }
                // 添加一个常量值 表示名单编号
                if(hasConstant){
                    for(String cons : constantValue){
                        sb.append(cons);
                        sb.append(splitLabel);
                    }
                }
                // 添加一个账号类型，取值范围为1-4
                int accoutType = getBoundValue(1,4);
                sb.append(accoutType);
                sb.append(splitLabel);

                // 添加一个账号号码
                String list0 = list.get(0);
                String[] res0 = list0.split("_");
                Object obj0 = generateValue(res0[0], Integer.parseInt(res0[1]));
                sb.append(obj0.toString());
                sb.append(splitLabel);

                // 添加一个客户名称
                String list1 = list.get(1);
                String[] res1 = list1.split("_");
                Object obj1 = generateValue(res1[0], Integer.parseInt(res1[1]));
                sb.append(obj1.toString());
                sb.append(splitLabel);

                // 添加一个证件类型
                Character character = characters.get(new Random().nextInt(characters.size()));
                sb.append(character);
                sb.append(splitLabel);

                // 添加一个证件号码
                String list2 = list.get(2);
                String[] res2 = list2.split("_");
                Object obj2 = generateValue(res2[0], Integer.parseInt(res2[1]));
                sb.append(obj2.toString());
                sb.append(splitLabel);

                // 核心客户号
                Integer host_cust = generateID(1);
                sb.append(host_cust.toString());
                sb.append(splitLabel);
                // 添加手机号码
                String list3 = list.get(3);
                String[] res3 = list3.split("_");
                Object obj3 = generateValue(res3[0], Integer.parseInt(res3[1]));
                sb.append(obj3.toString());
                sb.append(splitLabel);

                // 添加最大购买金额
                String list4 = list.get(4);
                String[] res4 = list4.split("_");
                Object obj4 = generateValue(res4[0], Integer.parseInt(res4[1]));
                sb.append(obj4.toString());
                sb.append(splitLabel);

                // 核心客户号 cust_no
                Integer custNo = generateID(2);
                sb.append(custNo.toString());
                sb.append(splitLabel);

//                for (String str : list) {
//                    String[] res = str.split("_");
//                    Object obj = generateValue(res[0], Integer.parseInt(res[1]));
//
//                    sb.append(obj.toString());
//                    sb.append(splitLabel);
//                }
                bufferedWriter.write(sb.toString());
                bufferedWriter.newLine();
                if (i % 10000 == 0) { // 每写一段数据存入文本
                    bufferedWriter.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    /**
    * 功能描述: 用于生成指定范围内的数值
    * @auther: zwy
    */
    private int getBoundValue(int min,int max) {
//        List<Integer> value = new ArrayList<>();
//        for (int i = min; i < max; i++) {
//            value.add(i);
//        }
//        int ind = ((int)Math.random()*10) * (max-min);
//        return value.get(ind);
        return new Random().nextInt(max)+1;
    }
    /**
     * 功能描述: 主键生成方式
     *
     * @auther: zwy
     */
    private int generateID(int type) {
        if (type == 0) { // 主键自增
            return ID++;
        } else if(type == 1) {// 添加其他id生成方式
            return host_cust_no++;
        }else if(type == 2){
            return cust_no++;
        }
        return ID++;
    }

    /**
     * 功能描述: 测试数据生成是否成立
     *
     * @auther: zwy
     */
    @Test
    public void test() {
        // 获取字符串
        String genStr = (String) generateValue("VARCHAR", 10);
        System.out.println(genStr);

        // 获取数值型整数,第二个数值对于数值而言，表示数值长度
        Integer genInt = (Integer) generateValue("INT", 3);
        System.out.println(genInt);

        // 获取一个浮点型数值
        Double dou = (Double) generateValue("DOUBLE", 3);
        System.out.println(dou);

        // 获取一个DATA类型的数值
        String date = (String) generateValue("DATE", 0);
        System.out.println(date);
    }


    public Object generateValue(String type, Integer len) {
        Object object = null;
        switch (SqlType.valueOf(type)) {
            case VARCHAR:
//                System.out.println("here is to generate string value");
                object = generateString(len);
                break;
            case INT:
//                System.out.println("here is to generate int value");
                object = generateInteger(len);
                break;
            case DOUBLE:
//                System.out.println("here is to generate float value");
                object = generateDouble(len);
                break;
            case DATE:
//                System.out.println("here is to generate time value");
                object = generateDate();
            default:
                break;
        }
        return object;
    }

    /**
     * 功能描述: 生成时间字符串
     * @auther: zwy
     */
    private String generateDate() {
//        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        return sim.format(new Date());
    }

    /**
     * 功能描述: 生成指定长度的Double数值
     * @auther: zwy
     */
    private Double generateDouble(Integer len) {
        Double rest = 0.0;
        for (int i = 0; i < len; i++) {
            rest += Math.random() * Math.pow(10, i + 1);
        }
        BigDecimal bigDecimal = new BigDecimal(rest).setScale(DOUBLE_DECIMAL, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * 功能描述: 生成指定长度的Integer数值
     * @auther: zwy
     */
    private Integer generateInteger(Integer len) {
        Integer result = 0;
        // 这里对数值进行拼接处理吧,以每8位进行拼接处理
//        Integer iters = Math.round(len / 8);
//        Integer res = len % 8;
//        StringBuilder resultStr = new StringBuilder();
//
//        for (int j = 1; j <= iters; j++) {
//            for (int i = 0; i < 8; i++) {
//                result += (int)(Math.random() * Math.pow(10, i + 1));
//            }
//            resultStr.append(result);
//        }
//        result = 0;
        for (int i = 0; i < len; i++) {
            result += (int) (Math.random() * Math.pow(10, i + 1));
        }
//        resultStr.append(result);
        return result;
    }

    /**
     * 功能描述: 用于生成len长度的字符串
     *
     * @auther: zwy
     */
    private String generateString(Integer len) {
        // 1 获取文本数据源
        String sourceString = sourceString();
        StringBuilder target = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int ran = (int) (Math.random() * sourceString.length());
            target.append(sourceString.substring(ran, ran + 1));
        }
        return target.toString();
    }

    /**
     * 功能描述: 读取文本源数据
     *
     * @auther: zwy
     */
    private String sourceString() {
        StringBuilder sb = new StringBuilder();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("D:/BaiduNetdiskDownload/SSM整合案例/idea/heima_ssm/gennerStringSource.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                sb.append(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e2) {
                }
            }
        }
        return sb.toString();
    }

    /**
     * 功能描述: 测试enum是否有效
     *
     * @auther: zwy
     */
    @Test
    public void testEnum() {
        String varchar = "VARCHAR";

        switch (SqlType.valueOf(varchar)) {
            case VARCHAR:
                System.out.println("here is to generate string value");
                break;
            case INT:
                System.out.println("here is to generate int value");
                break;
            case DOUBLE:
                System.out.println("here is to generate float value");
                break;
            case DATE:
                System.out.println("here is to generate time value");
            default:
                break;
        }
    }

    /**
     * 功能描述: 文件读取练习
     *
     * @auther: zwy
     */
    @Test
    public void test5() {
        // 读文本文件
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("generateData.txt"); // 使用默认的编码方式编码GBK
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e2) {
                }
            }
        }
    }
}

package com.demo.java_base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.demo.io_jdbc.entity.Table;
import com.demo.io_jdbc.entity.TableColumn;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Auther: zwy
 * @Date: 2020/11/18
 * @Description: com.demo.java_base
 * @version:
 */
public class CreateSqlByExcel {
    //导出sql数据库类型
    private static String OUT_TYPE = "mysql";
    //使用表空间
    private static String TABLE_SPACE = "";
    //excel文件路径
    private static String FILE_PATH = "D://kayak/crtSql/";

//


    // 理财数据表
    private static String FILE_NAME = "数据表.xlsx";
    private static String SYSTEMID = "SJB";

//    ========================== 以下库运行成功 ==========================
    // 组合中心
//    private static String FILE_NAME = "4组合中心库.xlsx";
//    private static String SYSTEMID = "ZH";

//    // 6转让中心库.xlsx
//    private static String FILE_NAME = "6转让中心库.xlsx";
//    private static String SYSTEMID = "ZR";

    // 7定投中心库.xlsx
//    private static String FILE_NAME = "7定投中心库.xlsx";
//    private static String SYSTEMID = "DT";

////    理财代销交易库.xlsx
//    private static String FILE_NAME = "理财代销交易库.xlsx";
//    private static String SYSTEMID = "LC";

    //  平台中心.xlsx
//    private static String FILE_NAME = "平台中心.xlsx";
//    private static String SYSTEMID = "PT";

    // 产品中心库.xlsx
//    private static String FILE_NAME = "产品中心库.xlsx";
//    private static String SYSTEMID = "CP";

    //    理财代销参数库.xlsx
//    private static String FILE_NAME = "理财代销参数库.xlsx";
//    private static String SYSTEMID = "LCDX";

    // 支付中心
//    private static String FILE_NAME = "5支付中心库.xlsx";
//    private static String SYSTEMID = "ZF";

    //excel文件名
//    private static String FILE_NAME = "2客户中心库.xlsx";
    //导出文件名为 create_[SYSTEMID].sql
//    private static String SYSTEMID = "KH";

    // 清算
//    private static String FILE_NAME = "清算表.xlsx";
//    private static String SYSTEMID = "QSB";

    private static String TYPE_MYSQL = "mysql";
    private static String TYPE_ORACLE = "oracle";
    private static String TYPE_DB2 = "db2";


    private static String DATA_TYPE_DC = "定长";
    private static String DATA_TYPE_BDC = "不定长";
    private static String DATA_TYPE_SZ = "数值";
    private static String DATA_TYPE_ZX = "整型";
    private static String DATA_TYPE_SJC = "时间戳";

    private static String YES_OR_NO_YES = "是";
    private static String YES_OR_NO_NO = "否";


    public static void main(String[] args) throws Exception {

        Map<String, Map<String, String>> columns = new HashMap<>();
        Map<String, String> errorMsg1 = new HashMap<>();

        Map<String, Map<String, String>> columns2 = new HashMap<>();
        Map<String, String> errorMsg2 = new HashMap<>();

        Map<String, String> dataTypeMap = new HashMap<>();
        //MYSQL
        dataTypeMap.put(TYPE_MYSQL + DATA_TYPE_DC, "CHAR");
        dataTypeMap.put(TYPE_MYSQL + DATA_TYPE_BDC, "VARCHAR");
        dataTypeMap.put(TYPE_MYSQL + DATA_TYPE_SZ, "DECIMAL");
        dataTypeMap.put(TYPE_MYSQL + DATA_TYPE_ZX, "INT");
        dataTypeMap.put(TYPE_MYSQL + DATA_TYPE_SJC, "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP");

        //ORACLE
        dataTypeMap.put(TYPE_ORACLE + DATA_TYPE_DC, "CHAR");
        dataTypeMap.put(TYPE_ORACLE + DATA_TYPE_BDC, "VARCHAR2");
        dataTypeMap.put(TYPE_ORACLE + DATA_TYPE_SZ, "NUMBER");
        dataTypeMap.put(TYPE_ORACLE + DATA_TYPE_ZX, "INT");
        dataTypeMap.put(TYPE_ORACLE + DATA_TYPE_SJC, "DATE");

        //DB2
        dataTypeMap.put(TYPE_DB2 + DATA_TYPE_DC, "CHAR");
        dataTypeMap.put(TYPE_DB2 + DATA_TYPE_BDC, "VARCHAR");
        dataTypeMap.put(TYPE_DB2 + DATA_TYPE_SZ, "DECIMAL");
        dataTypeMap.put(TYPE_DB2 + DATA_TYPE_ZX, "INT");

        List<Table> tables = new ArrayList<>();
        Map<String, List<TableColumn>> tableColumns = new HashMap<>();

        Map<String, StringBuffer> sf = new HashMap<>();

        FileWriter fileWritter = null;

        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;

        try {
            System.out.println(FILE_PATH+FILE_NAME);
            wb = readExcel(FILE_PATH + FILE_NAME);

            if (wb != null) {

                int sheetnum = wb.getNumberOfSheets();
                //System.out.println("sheetnum【"+sheetnum+"】");

                for (int z = 0; z < sheetnum; z++) {
                    //获取第z个sheet
                    sheet = wb.getSheetAt(z);
                    System.out.println("第【" + z + "】sheet【" + sheet.getSheetName() + "】");

                    String tableName = "";
                    String tableCode = "";
                    //获取最大行数
                    int rownum = sheet.getPhysicalNumberOfRows();

                    tableName = sheet.getSheetName();
                    System.out.println("tableName[" + tableName + "]");

                    for (int i = 0; i < rownum; i++) {
                        row = sheet.getRow(i);
                        //第一行 表名称|
                        if (i == 0) {
                            //tableName = (String) getCellFormatValue(row.getCell(1));
                            //System.out.println("tableName["+tableName+"]");
                        }
                        //第二行 表CODE|
                        if (i == 1) {
                            tableCode = (String) getCellFormatValue(row.getCell(1));
                            System.out.println("tableCode[" + tableCode + "]");
                        }

                        //第三行  表头
                        //第四行 表字段开始
                        if (i > 2) {
                            //获取最大列数
                            int colnum = row.getPhysicalNumberOfCells();
                            //序号	字段名称	字段代码	字段类型	字段长度	小数位数	字段描述    默认值	数据字典值	是否主键	是否必填
                            if (colnum < 11) {
                                System.out.println("第[" + (i + 1) + "]行，colnum[" + colnum + "]");
                                throw new Exception("excel格式不正确，字段列数不符");
                            }


                            TableColumn ntc = new TableColumn();
                            String column_name = (String) getCellFormatValue(row.getCell(1));
                            String column_code = (String) getCellFormatValue(row.getCell(2));
                            String column_data_type = (String) getCellFormatValue(row.getCell(3));
                            String column_length = (String) getCellFormatValue(row.getCell(4));
                            String column_precision = (String) getCellFormatValue(row.getCell(5));
                            String column_comment = (String) getCellFormatValue(row.getCell(6));
                            String column_default = (String) getCellFormatValue(row.getCell(7));
                            String column_dict = (String) getCellFormatValue(row.getCell(8));
                            String column_primary = (String) getCellFormatValue(row.getCell(9));
                            String column_mandatory = (String) getCellFormatValue(row.getCell(10));

                            if (column_code != null && !"".equals(column_code)) {

                                if (!(DATA_TYPE_DC.equals(column_data_type)
                                        || DATA_TYPE_BDC.equals(column_data_type)
                                        || DATA_TYPE_SZ.equals(column_data_type)
                                        || DATA_TYPE_ZX.equals(column_data_type)
                                        || DATA_TYPE_SJC.equals(column_data_type)
                                )) {
                                    throw new Exception("字段类型不支持[" + tableName + "]的[" + (i + 1) + "]行[" + column_code + "]");
                                }
                                if (!(YES_OR_NO_YES.equals(column_primary) || YES_OR_NO_NO.equals(column_primary))) {
                                    throw new Exception("是否主键格式不对，[" + tableName + "]的[" + (i + 1) + "]行[" + column_code + "]");
                                }
                                if (!(YES_OR_NO_YES.equals(column_mandatory) || YES_OR_NO_NO.equals(column_mandatory))) {
                                    throw new Exception("是否必填格式不对，[" + tableName + "]的[" + (i + 1) + "]行[" + column_code + "]");
                                }
                                ntc.setTable_code(tableCode);
                                ntc.setColumn_code(column_code);
                                ntc.setColumn_name(column_name);
                                ntc.setColumn_comment(column_comment);
                                ntc.setColumn_data_type(column_data_type);
                                ntc.setColumn_length(column_length);
                                ntc.setColumn_precision(column_precision);
                                ntc.setColumn_primary(column_primary);
                                ntc.setColumn_mandatory(column_mandatory);
                                ntc.setColumn_default(column_default);
                                ntc.setColumn_dict(column_dict);

                                if (tableColumns.get(SYSTEMID + "_" + tableCode) != null) {
                                    List<TableColumn> list1 = tableColumns.get(SYSTEMID + "_" + tableCode);
                                    list1.add(ntc);
                                    tableColumns.put(SYSTEMID + "_" + tableCode, list1);
                                } else {
                                    List<TableColumn> list1 = new ArrayList<>();
                                    list1.add(ntc);
                                    tableColumns.put(SYSTEMID + "_" + tableCode, list1);
                                }

                                //
                                if (column_precision == null) {
                                    column_precision = "";
                                }

                                if (columns2.get(column_name) != null) {
                                    Map<String, String> columnMap = columns.get(column_name);
                                    String oColumnCode = columnMap.get("column_code");
                                    //String oColumnDataType = columnMap.get("column_data_type");
                                    //String oColumnName = columnMap.get("column_name");
                                    //String oColumnLength = columnMap.get("column_length");
                                    //String oColumnPercision = columnMap.get("column_precision");
                                    String oTableCode = columnMap.get("table_code");

                                    if (!oColumnCode.equals(column_code)) {
                                        String str1 = "[" + tableCode + "]表中，字段[" + column_name + "]的code[" + (column_code +
                                                "]与[" + oTableCode + "表中[" + oColumnCode) + "]不一致";
                                        //System.out.println(str1);

                                        if (errorMsg2.get(column_name) != null) {
                                            String str = errorMsg2.get(column_name);
                                            errorMsg2.put(column_name, str + "\n" + str1);
                                        } else {
                                            errorMsg2.put(column_name, str1);
                                        }

                                    }

                                } else {
                                    Map<String, String> columnMap = new HashMap<>();

                                    columnMap.put("table_code", tableCode);
                                    columnMap.put("column_code", column_code);
                                    columnMap.put("column_name", column_name);
                                    columnMap.put("column_precision", column_precision);
                                    columnMap.put("column_data_type", column_data_type);
                                    columnMap.put("column_length", column_length);

                                    columns.put(column_name, columnMap);
                                }

                                if (columns.get(column_code) != null) {
                                    Map<String, String> columnMap = columns.get(column_code);
                                    String oColumnDataType = columnMap.get("column_data_type");
                                    String oColumnName = columnMap.get("column_name");
                                    String oColumnLength = columnMap.get("column_length");
                                    String oColumnPercision = columnMap.get("column_precision");
                                    String oTableCode = columnMap.get("table_code");

                                    if (!oColumnDataType.equals(column_data_type)) {
                                        String str1 = "[" + tableCode + "]表中，column_code[" + column_code + "]的类型[" + (column_data_type +
                                                "]与[" + oTableCode + "表中[" + oColumnDataType) + "]不一致";
                                        //System.out.println(str1);

                                        if (errorMsg1.get(column_code) != null) {
                                            String str = errorMsg1.get(column_code);
                                            errorMsg1.put(column_code, str + "\n" + str1);
                                        } else {
                                            errorMsg1.put(column_code, str1);
                                        }

                                    }

                                    if (!oColumnLength.equals(column_length)) {


                                        String str1 = "[" + tableCode + "]表中，column_code[" + column_code + "]的长度[" + (column_length +
                                                "]与[" + oTableCode + "表中[" + oColumnLength) + "]不一致";
                                        //System.out.println(str1);

                                        if (errorMsg1.get(column_code) != null) {
                                            String str = errorMsg1.get(column_code);
                                            errorMsg1.put(column_code, str + "\n" + str1);
                                        } else {
                                            errorMsg1.put(column_code, str1);
                                        }

                                    }

                                    if (!oColumnPercision.equals(column_precision)) {

                                        String str1 = "[" + tableCode + "]表中，column_code[" + column_code + "]的精度[" + (column_precision +
                                                "]与[" + oTableCode + "表中[" + oColumnPercision) + "]不一致";
                                        //System.out.println(str1);

                                        if (errorMsg1.get(column_code) != null) {
                                            String str = errorMsg1.get(column_code);
                                            errorMsg1.put(column_code, str + "\n" + str1);
                                        } else {
                                            errorMsg1.put(column_code, str1);
                                        }

                                    }

                                    if (!oColumnName.equals(column_name)) {

                                        String str1 = "[" + tableCode + "]表中，column_code[" + column_code + "]的名称[" + (column_name +
                                                "]与[" + oTableCode + "表中[" + oColumnName) + "]不一致";
                                        //System.out.println(str1);

                                        if (errorMsg1.get(column_code) != null) {
                                            String str = errorMsg1.get(column_code);
                                            errorMsg1.put(column_code, str + "\n" + str1);
                                        } else {
                                            errorMsg1.put(column_code, str1);
                                        }

                                    }
                                } else {
                                    Map<String, String> columnMap = new HashMap<>();

                                    columnMap.put("table_code", tableCode);
                                    columnMap.put("column_code", column_code);
                                    columnMap.put("column_name", column_name);
                                    columnMap.put("column_precision", column_precision);
                                    columnMap.put("column_data_type", column_data_type);
                                    columnMap.put("column_length", column_length);

                                    columns.put(column_code, columnMap);
                                }
                            }

                        }

                    }

                    Table newTable = new Table();
                    newTable.setTable_code(tableCode);
                    newTable.setTable_name(tableName);
                    newTable.setTable_comment(tableName);
                    newTable.setTable_space(TABLE_SPACE);

                    tables.add(newTable);
                }
            }


            for (Table newTable : tables) {
                String table_code = newTable.getTable_code();
                //String system_id = SYSTEMID;
                //System.out.println("****system_id:["+system_id+"],table_code:["+table_code+"]");
                String primaryKey = "";

                StringBuffer sbc = new StringBuffer();
                StringBuffer sbd = new StringBuffer();

                //mysql
                if (OUT_TYPE.equals(TYPE_MYSQL)) {
                    //===============================================MYSQL==========================================================
                    sbd.append("\nDROP TABLE IF EXISTS " + table_code + ";\n");
                    sbc.append("\nCREATE TABLE " + table_code + " (\n");

                    List<TableColumn> newTableColumnList = tableColumns.get(SYSTEMID + "_" + table_code);
                    if (newTableColumnList != null && newTableColumnList.size() > 0) {
                        for (TableColumn newTableColumn : newTableColumnList) {
                            //System.out.println("%%%%column_code:["+newTableColumn.getColumn_code()+"]");
                            sbc.append("\t");
                            sbc.append(newTableColumn.getColumn_code());

                            sbc.append(" ");
                            sbc.append(dataTypeMap.get(OUT_TYPE + newTableColumn.getColumn_data_type()));

                            if (DATA_TYPE_SJC.equals(newTableColumn.getColumn_data_type())) {
                                //时间戳后面不用加长度贺精度
                                sbc.append(" ");
                            } else if (DATA_TYPE_ZX.equals(newTableColumn.getColumn_data_type())) {
                                //整型
                                if (newTableColumn.getColumn_length() != null
                                        && !"".equals(newTableColumn.getColumn_length())) {
                                    sbc.append("(");
                                    sbc.append(newTableColumn.getColumn_length());
                                    sbc.append(") ");
                                }

                            } else {
                                // 定长 不定长 数值
                                sbc.append("(");
                                sbc.append(newTableColumn.getColumn_length());
                                if (DATA_TYPE_SZ.equals(newTableColumn.getColumn_data_type())) {
                                    sbc.append(", ");
                                    sbc.append(newTableColumn.getColumn_precision());
                                }
                                sbc.append(") ");
                            }

                            if (YES_OR_NO_YES.equals(newTableColumn.getColumn_mandatory())) {
                                sbc.append("NOT NULL ");
                            }
                            if (YES_OR_NO_YES.equals(newTableColumn.getColumn_primary())) {
                                primaryKey = primaryKey + newTableColumn.getColumn_code() + ", ";
                            }

                            if (newTableColumn.getColumn_default() != null
                                    && !"".equals(newTableColumn.getColumn_default())) {
                                sbc.append("DEFAULT ");
                                if (DATA_TYPE_DC.equals(newTableColumn.getColumn_data_type())
                                        || DATA_TYPE_BDC.equals(newTableColumn.getColumn_data_type())) {
                                    sbc.append("'");
                                    sbc.append(newTableColumn.getColumn_default());
                                    sbc.append("' ");
                                } else {
                                    sbc.append(newTableColumn.getColumn_default());
                                    sbc.append(" ");
                                }

                            }
                            if (newTableColumn.getColumn_comment() != null) {
                                sbc.append("COMMENT '");
                                sbc.append(newTableColumn.getColumn_comment());
                                sbc.append("'");
                            }
                            sbc.append(",\n");
                        }
                    }
                    //加主键
                    if (primaryKey != null && !"".equals(primaryKey)) {
                        sbc.append("\tPRIMARY KEY (");
                        sbc.append(primaryKey.substring(0, primaryKey.length() - 2));
                        sbc.append(")\n");
                    }
                    sbc.append(")ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_BIN;\n\n");

                    sbc.append("ALTER TABLE ");
                    sbc.append(newTable.getTable_code());
                    sbc.append(" COMMENT '");
                    sbc.append(newTable.getTable_comment());
                    sbc.append("';\n\n");
                    //===============================================MYSQL==========================================================
                } else if (OUT_TYPE.equals(TYPE_ORACLE)) {
                    //===============================================ORACLE==========================================================
                    sbd.append("\nDROP TABLE  " + table_code + " CASCADE CONSTRAINTS;\n");
                    sbc.append("\nCREATE TABLE " + table_code + " (\n");

                    StringBuffer sbcoment = new StringBuffer();

                    List<TableColumn> newTableColumnList = tableColumns.get(SYSTEMID + "_" + table_code);
                    if (newTableColumnList != null && newTableColumnList.size() > 0) {
                        for (TableColumn newTableColumn : newTableColumnList) {
                            //System.out.println("%%%%column_code:["+newTableColumn.getColumn_code()+"]");
                            sbc.append("\t");
                            sbc.append(newTableColumn.getColumn_code());

                            sbc.append(" ");
                            sbc.append(dataTypeMap.get(OUT_TYPE + newTableColumn.getColumn_data_type()));

                            sbc.append("(");
                            sbc.append(newTableColumn.getColumn_length());

                            if (DATA_TYPE_SZ.equals(newTableColumn.getColumn_data_type())) {
                                sbc.append(", ");
                                sbc.append(newTableColumn.getColumn_precision());
                            }
                            sbc.append(") ");

                            if (newTableColumn.getColumn_default() != null
                                    && !"".equals(newTableColumn.getColumn_default())) {
                                sbc.append("DEFAULT ");
                                if (DATA_TYPE_DC.equals(newTableColumn.getColumn_data_type())
                                        || DATA_TYPE_BDC.equals(newTableColumn.getColumn_data_type())) {
                                    sbc.append("'");
                                    sbc.append(newTableColumn.getColumn_default());
                                    sbc.append("' ");
                                } else {
                                    sbc.append(newTableColumn.getColumn_default());
                                    sbc.append(" ");
                                }

                            }

                            if (YES_OR_NO_YES.equals(newTableColumn.getColumn_mandatory())) {
                                sbc.append("NOT NULL ");
                            }
                            if (YES_OR_NO_YES.equals(newTableColumn.getColumn_primary())) {
                                primaryKey = primaryKey + newTableColumn.getColumn_code() + ", ";
                            }

                            if (newTableColumn.getColumn_comment() != null) {
                                sbcoment.append("COMMENT ON COLUMN ");
                                sbcoment.append(newTableColumn.getTable_code());
                                sbcoment.append(".");
                                sbcoment.append(newTableColumn.getColumn_code());
                                sbcoment.append(" IS '");
                                sbcoment.append(newTableColumn.getColumn_comment());
                                sbcoment.append("';\n");
                            }
                            sbc.append(",\n");
                        }
                    }
                    //加主键
                    if (primaryKey != null && !"".equals(primaryKey)) {
                        sbc.append("\tCONSTRAINT PK_");
                        sbc.append(newTable.getTable_code());
                        sbc.append(" PRIMARY KEY (");
                        sbc.append(primaryKey.substring(0, primaryKey.length() - 2));
                        sbc.append(")\n");
                    }
                    sbc.append(");\n\n");

                    sbc.append("COMMENT ON TABLE ");
                    sbc.append(newTable.getTable_code());
                    sbc.append(" IS '");
                    sbc.append(newTable.getTable_comment());
                    sbc.append("';\n\n");

                    sbc.append(sbcoment);
                    //===============================================ORACLE==========================================================
                }


                if (sf.get("create_" + SYSTEMID) != null) {
                    StringBuffer sbbc = sf.get("create_" + SYSTEMID);
                    sbbc.append(sbc);
                    sf.put("create_" + SYSTEMID, sbbc);
                } else {
                    sf.put("create_" + SYSTEMID, sbc);
                }

                if (sf.get("drop_" + SYSTEMID) != null) {
                    StringBuffer sbbd = sf.get("drop_" + SYSTEMID);
                    sbbd.append(sbd);
                    sf.put("drop_" + SYSTEMID, sbbd);
                } else {
                    sf.put("drop_" + SYSTEMID, sbd);
                }
            }

            File folder = new File(FILE_PATH);
            if (!folder.exists()) {
                folder.mkdirs();//创建目录
            }

            //写文件
            for (String key : sf.keySet()) {
                //			File file =new File(filePath+File.separator+"create_"+key+".sql");
                //	        if(!file.exists()){
                //	        	file.createNewFile();
                //	        }
                //System.out.println("key:"+key);
                //System.out.println("sf.get(key):"+sf.get(key).toString());

                fileWritter = new FileWriter(FILE_PATH + File.separator + key + ".sql", false);
                fileWritter.write(sf.get(key).toString());
                fileWritter.flush();
            }


            System.out.println("字段code相同，其他不同的列：");
            for (String key : errorMsg1.keySet()) {
                System.out.println(errorMsg1.get(key));
            }
            System.out.println("字段名相同，code不同的列：");
            for (String key : errorMsg2.keySet()) {
                System.out.println(errorMsg2.get(key));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (wb != null) {
//                wb.close();
            }
        }

    }


    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    @SuppressWarnings("deprecation")
    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    String tmp = String.valueOf(cell.getNumericCellValue());
                    cellValue = tmp.substring(0, tmp.length() - 2);
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}

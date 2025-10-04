package com.example.autobuild.equityreport.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel模板解析器
 * 用于解析Excel模板文件并生成对应的CSV数据
 */
public class ExcelTemplateParser {
    
    /**
     * 解析Excel模板文件
     * 
     * @param excelFilePath Excel文件路径
     * @return 解析后的数据行列表
     */
    public static List<String[]> parseExcelTemplate(String excelFilePath) throws IOException {
        List<String[]> result = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
            
            // 读取所有行
            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                
                for (Cell cell : row) {
                    String cellValue = getCellValueAsString(cell);
                    rowData.add(cellValue);
                }
                
                // 转换为数组
                String[] rowArray = rowData.toArray(new String[0]);
                result.add(rowArray);
            }
        }
        
        return result;
    }
    
    /**
     * 获取单元格值作为字符串
     * 
     * @param cell 单元格
     * @return 字符串值
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // 处理数字，避免科学计数法
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == (long) numericValue) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    
    /**
     * 将解析结果转换为CSV格式
     * 
     * @param data 解析后的数据
     * @return CSV格式的字符串
     */
    public static String convertToCsv(List<String[]> data) {
        StringBuilder csv = new StringBuilder();
        
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    csv.append(",");
                }
                csv.append(row[j]);
            }
            if (i < data.size() - 1) {
                csv.append("\n");
            }
        }
        
        return csv.toString();
    }
}

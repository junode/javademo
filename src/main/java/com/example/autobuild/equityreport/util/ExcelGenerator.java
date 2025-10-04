package com.example.autobuild.equityreport.util;

import com.example.autobuild.equityreport.model.SimpleEquityReportRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Excel文件生成器
 * 使用Apache POI生成真正的Excel文件
 */
public class ExcelGenerator {
    
    /**
     * 生成Excel文件
     * 
     * @param outputPath 输出文件路径
     * @param reportData 报表数据
     * @throws IOException IO异常
     */
    public static void generateExcel(String outputPath, List<SimpleEquityReportRow> reportData) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("权益日报");
            
            // 创建标题样式
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle numberStyle = createNumberStyle(workbook);
            
            // 写入标题行
            createTitleRow(sheet, titleStyle);
            
            // 写入列标题行
            createHeaderRow(sheet, headerStyle);
            
            // 写入数据行
            createDataRows(sheet, reportData, dataStyle, numberStyle);
            
            // 自动调整列宽
            autoSizeColumns(sheet);
            
            // 写入文件
            try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
                workbook.write(fileOut);
            }
        }
    }
    
    /**
     * 创建标题样式
     */
    private static CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建表头样式
     */
    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }
    
    /**
     * 创建数据样式
     */
    private static CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }
    
    /**
     * 创建数字样式
     */
    private static CellStyle createNumberStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setDataFormat(workbook.createDataFormat().getFormat("0.0000"));
        return style;
    }
    
    /**
     * 创建标题行
     */
    private static void createTitleRow(Sheet sheet, CellStyle titleStyle) {
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("权益日报");
        titleCell.setCellStyle(titleStyle);
        
        // 合并标题单元格
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 14));
    }
    
    /**
     * 创建表头行
     */
    private static void createHeaderRow(Sheet sheet, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(1);
        
        String[] headers = {
            "产品", "代码", "责任人", "产品形式", "起期", "收益目标", "自定义收益目标", "国家标识",
            "年初", "期末", "平均", "收益率", "基准收益率", "超额收益率", "分类",
            "年化波动率", "跟踪误差", "夏普比率", "信息比率", "Sortino比率",
            "最近一年最大回撤", "最近一年超额最大回撤", "Calmar比率", "超额Calmar比率",
            "今年以来Calmar比率", "今年以来超额Calmar比率"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }
    
    /**
     * 创建数据行
     */
    private static void createDataRows(Sheet sheet, List<SimpleEquityReportRow> reportData, 
                                     CellStyle dataStyle, CellStyle numberStyle) {
        int rowIndex = 2; // 从第3行开始（索引为2）
        
        for (SimpleEquityReportRow row : reportData) {
            Row dataRow = sheet.createRow(rowIndex++);
            
            // 基础信息
            createCell(dataRow, 0, row.getProduct(), dataStyle);
            createCell(dataRow, 1, row.getCode(), dataStyle);
            createCell(dataRow, 2, row.getResponsibility(), dataStyle);
            createCell(dataRow, 3, row.getProductType(), dataStyle);
            createCell(dataRow, 4, row.getStartDate(), dataStyle);
            createCell(dataRow, 5, row.getReturnTarget(), numberStyle);
            createCell(dataRow, 6, row.getCustomReturnTarget(), numberStyle);
            createCell(dataRow, 7, row.getCountryId(), dataStyle);
            
            // 规模数据
            createCell(dataRow, 8, row.getBeginSize(), numberStyle);
            createCell(dataRow, 9, row.getEndSize(), numberStyle);
            createCell(dataRow, 10, row.getAvgSize(), numberStyle);
            
            // 收益率数据
            createCell(dataRow, 11, row.getYield(), numberStyle);
            createCell(dataRow, 12, row.getBenchmarkYield(), numberStyle);
            createCell(dataRow, 13, row.getExcessYield(), numberStyle);
            createCell(dataRow, 14, row.getCategory(), dataStyle);
            
            // 风险指标数据
            createCell(dataRow, 15, row.getVolatility(), numberStyle);
            createCell(dataRow, 16, row.getTrackingError(), numberStyle);
            createCell(dataRow, 17, row.getSharper(), numberStyle);
            createCell(dataRow, 18, row.getInfoRate(), numberStyle);
            createCell(dataRow, 19, row.getSortino(), numberStyle);
            createCell(dataRow, 20, row.getRecentYearMaxDraw(), numberStyle);
            createCell(dataRow, 21, row.getRecentSuperMaxdraw(), numberStyle);
            createCell(dataRow, 22, row.getCalmar(), numberStyle);
            createCell(dataRow, 23, row.getSuperCalmar(), numberStyle);
            createCell(dataRow, 24, row.getYtdCalmar(), numberStyle);
            createCell(dataRow, 25, row.getYtdSuperCalmar(), numberStyle);
        }
    }
    
    /**
     * 创建单元格
     */
    private static void createCell(Row row, int columnIndex, Object value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else {
            cell.setCellValue(value.toString());
        }
        
        cell.setCellStyle(style);
    }
    
    /**
     * 自动调整列宽
     */
    private static void autoSizeColumns(Sheet sheet) {
        for (int i = 0; i < 26; i++) {
            sheet.autoSizeColumn(i);
            // 设置最小和最大列宽
            int currentWidth = sheet.getColumnWidth(i);
            int minWidth = 2000; // 最小宽度
            int maxWidth = 8000; // 最大宽度
            
            if (currentWidth < minWidth) {
                sheet.setColumnWidth(i, minWidth);
            } else if (currentWidth > maxWidth) {
                sheet.setColumnWidth(i, maxWidth);
            }
        }
    }
}

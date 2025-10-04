package com.example.autobuild.equityreport.util;

import com.example.autobuild.equityreport.model.SimpleEquityReportRow;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 简单Excel生成器
 * 使用CSV格式生成Excel兼容的文件
 */
public class SimpleExcelGenerator {
    
    /**
     * 生成Excel兼容的CSV文件
     * 
     * @param outputPath 输出文件路径
     * @param reportData 权益日报数据
     * @throws IOException IO异常
     */
    public static void generateExcelCompatibleCsv(String outputPath, List<SimpleEquityReportRow> reportData) throws IOException {
        try (FileWriter writer = new FileWriter(outputPath)) {
            // 写入BOM以支持Excel正确识别UTF-8编码
            writer.write('\ufeff');
            
            // 写入标题行
            writer.write("产品,代码,责任人,产品形式,起期,收益目标,自定义收益目标,国家标识,年初规模,期末规模,平均规模,收益率,基准收益率,超额收益率,分类\n");
            
            // 写入数据行
            for (SimpleEquityReportRow row : reportData) {
                writer.write(generateCsvLine(row));
                writer.write("\n");
            }
        }
    }
    
    /**
     * 生成CSV行
     */
    private static String generateCsvLine(SimpleEquityReportRow row) {
        StringBuilder line = new StringBuilder();
        
        // 产品名称
        line.append(escapeCsvField(row.getProduct())).append(",");
        
        // 代码
        line.append(escapeCsvField(row.getCode())).append(",");
        
        // 责任人
        line.append(escapeCsvField(row.getResponsibility())).append(",");
        
        // 产品形式
        line.append(escapeCsvField(row.getProductType())).append(",");
        
        // 起期
        line.append(escapeCsvField(row.getStartDate())).append(",");
        
        // 收益目标
        line.append(formatDecimal(row.getReturnTarget())).append(",");
        
        // 自定义收益目标
        line.append(formatDecimal(row.getCustomReturnTarget())).append(",");
        
        // 国家标识
        line.append(escapeCsvField(row.getCountryId())).append(",");
        
        // 年初规模
        line.append(formatDecimal(row.getBeginSize())).append(",");
        
        // 期末规模
        line.append(formatDecimal(row.getEndSize())).append(",");
        
        // 平均规模
        line.append(formatDecimal(row.getAvgSize())).append(",");
        
        // 收益率
        line.append(formatDecimal(row.getYield())).append(",");
        
        // 基准收益率
        line.append(formatDecimal(row.getBenchmarkYield())).append(",");
        
        // 超额收益率
        line.append(formatDecimal(row.getExcessYield())).append(",");
        
        // 分类
        line.append(escapeCsvField(row.getCategory()));
        
        return line.toString();
    }
    
    /**
     * 转义CSV字段
     */
    private static String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
    
    /**
     * 格式化小数
     */
    private static String formatDecimal(java.math.BigDecimal value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
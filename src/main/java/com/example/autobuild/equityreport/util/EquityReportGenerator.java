package com.example.autobuild.equityreport.util;

import com.example.autobuild.equityreport.model.*;
import com.example.autobuild.equityreport.service.EquityReportDataService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权益日报生成器
 * 支持模板填充和Excel导出
 */
public class EquityReportGenerator {
    
    private final EquityReportDataService dataService;
    
    public EquityReportGenerator() {
        this.dataService = new EquityReportDataService();
    }
    
    /**
     * 从模板生成权益日报CSV文件
     * 
     * @param templatePath 模板文件路径
     * @param outputPath 输出文件路径
     * @throws IOException IO异常
     */
    public void generateEquityReportFromTemplate(String templatePath, String outputPath) throws IOException {
        // 读取模板文件
        List<String> templateLines = readTemplateFile(templatePath);
        
        // 生成权益日报数据
        List<EquityReportRow> reportData = dataService.generateEquityReportData();
        
        // 生成实体数据用于模板替换
        CalculateUnitDo calculateUnit = dataService.generateCalculateUnitDo();
        AssetDo asset = dataService.generateAssetDo();
        DwMarketBenchDo marketBench = dataService.generateDwMarketBenchDo();
        DwSecurityMarketDo securityMarket = dataService.generateDwSecurityMarketDo();
        
        // 创建数据映射
        Map<String, Object> dataMap = TemplateParser.createDataMap(calculateUnit, asset, marketBench, securityMarket);
        
        // 生成CSV内容
        List<String> csvLines = generateCsvContent(templateLines, reportData, dataMap);
        
        // 写入文件
        writeCsvFile(outputPath, csvLines);
    }
    
    /**
     * 生成权益日报Excel文件
     * 
     * @param outputPath 输出文件路径
     * @throws IOException IO异常
     */
    public void generateEquityReportExcel(String outputPath) throws IOException {
        // 生成权益日报数据
        List<EquityReportRow> reportData = dataService.generateEquityReportData();
        
        // 转换为SimpleEquityReportRow
        List<SimpleEquityReportRow> simpleData = convertToSimpleRows(reportData);
        
        // 使用SimpleExcelGenerator生成Excel兼容的CSV文件
        SimpleExcelGenerator.generateExcelCompatibleCsv(outputPath, simpleData);
    }
    
    /**
     * 转换EquityReportRow为SimpleEquityReportRow
     */
    private List<SimpleEquityReportRow> convertToSimpleRows(List<EquityReportRow> reportData) {
        List<SimpleEquityReportRow> simpleData = new ArrayList<>();
        for (EquityReportRow row : reportData) {
            SimpleEquityReportRow simpleRow = new SimpleEquityReportRow();
            simpleRow.setProduct(row.getProduct());
            simpleRow.setCode(row.getCode());
            simpleRow.setResponsibility(row.getResponsibility());
            simpleRow.setProductType(row.getProductType());
            simpleRow.setStartDate(row.getStartDate());
            simpleRow.setReturnTarget(row.getReturnTarget());
            simpleRow.setCustomReturnTarget(row.getCustomReturnTarget());
            simpleRow.setCountryId(row.getCountryId());
            simpleRow.setBeginSize(row.getBeginSize());
            simpleRow.setEndSize(row.getEndSize());
            simpleRow.setAvgSize(row.getAvgSize());
            simpleRow.setYield(row.getYield());
            simpleRow.setBenchmarkYield(row.getBenchmarkYield());
            simpleRow.setExcessYield(row.getExcessYield());
            simpleRow.setCategory(row.getCategory());
            simpleData.add(simpleRow);
        }
        return simpleData;
    }
    
    /**
     * 读取模板文件
     */
    private List<String> readTemplateFile(String templatePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
    
    /**
     * 生成CSV内容
     */
    private List<String> generateCsvContent(List<String> templateLines, 
                                          List<EquityReportRow> reportData, 
                                          Map<String, Object> dataMap) {
        List<String> csvLines = new ArrayList<>();
        
        // 添加标题行
        csvLines.add(templateLines.get(0)); // 人民币标题行
        csvLines.add(templateLines.get(1)); // 列标题行
        
        // 生成数据行
        for (EquityReportRow row : reportData) {
            String csvLine = generateCsvLine(row, dataMap);
            csvLines.add(csvLine);
        }
        
        return csvLines;
    }
    
    /**
     * 生成CSV行
     */
    private String generateCsvLine(EquityReportRow row, Map<String, Object> dataMap) {
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
        
        // 平均规模(2周)
        line.append(formatDecimal(row.getAvgSize2Weeks())).append(",");
        
        // 平均规模(含0)
        line.append(formatDecimal(row.getAvgSizeWithZero())).append(",");
        
        // YTD平均
        line.append(formatDecimal(row.getYtdAvg())).append(",");
        
        // 收益率
        line.append(formatDecimal(row.getYield())).append(",");
        
        // 基准收益率
        line.append(formatDecimal(row.getBenchmarkYield())).append(",");
        
        // 超额收益率
        line.append(formatDecimal(row.getExcessYield())).append(",");
        
        // 自定义目标收益率
        line.append(formatDecimal(row.getCustomTargetYield())).append(",");
        
        // 自定义超额收益率
        line.append(formatDecimal(row.getCustomExcessYield())).append(",");
        
        // 规模加权收益率(免税后)
        line.append(formatDecimal(row.getSizeWeightYieldAfterTax())).append(",");
        
        // 累计收益率(免税后)
        line.append(formatDecimal(row.getAccumYieldAfterTax())).append(",");
        
        // 收益额(百万,免税后)-CII
        line.append(formatDecimal(row.getIncomeCiiAfterTax())).append(",");
        
        // 收益额(百万)-TII
        line.append(formatDecimal(row.getIncomeTii())).append(",");
        
        // 超额收益(百万,免税后)
        line.append(formatDecimal(row.getExcessIncomeAfterTax())).append(",");
        
        // 自定义超额收益(百万,免税后)
        line.append(formatDecimal(row.getCustomExcessIncomeAfterTax())).append(",");
        
        // 规模加权收益率
        line.append(formatDecimal(row.getSizeWeightYield())).append(",");
        
        // 收益额(百万)
        line.append(formatDecimal(row.getIncome())).append(",");
        
        // 沪深300
        line.append(formatDecimal(row.getHs300())).append(",");
        
        // 中证800
        line.append(formatDecimal(row.getZs800())).append(",");
        
        // 创业板
        line.append(formatDecimal(row.getCyb())).append(",");
        
        // 中证红利
        line.append(formatDecimal(row.getZzhl())).append(",");
        
        // 国内基准
        line.append(formatDecimal(row.getDomesticBenchmark())).append(",");
        
        // OCI基准
        line.append(formatDecimal(row.getOciBenchmark())).append(",");
        
        // 海外基准
        line.append(formatDecimal(row.getOverseasBenchmark())).append(",");
        
        // 10d,99%
        line.append(formatDecimal(row.getTenDay99Percent())).append(",");
        
        // 收益额(百万,免税后)
        line.append(formatDecimal(row.getIncomeAfterTax())).append(",");
        
        // 规模变化(亿)
        line.append(formatDecimal(row.getSizeChange())).append(",");
        
        // 超额收益(百万,免税后)
        line.append(formatDecimal(row.getExcessIncomeAfterTax2())).append(",");
        
        // 收益率
        line.append(formatDecimal(row.getYield2())).append(",");
        
        // 基准收益率
        line.append(formatDecimal(row.getBenchmarkYield2())).append(",");
        
        // 超额收益率
        line.append(formatDecimal(row.getExcessYield2())).append(",");
        
        // 自定义目标收益率
        line.append(formatDecimal(row.getCustomTargetYield2())).append(",");
        
        // 自定义超额收益率
        line.append(formatDecimal(row.getCustomExcessYield2())).append(",");
        
        // 收益额(百万,免税后)
        line.append(formatDecimal(row.getIncomeAfterTax2())).append(",");
        
        // 规模变化(亿)
        line.append(formatDecimal(row.getSizeChange2())).append(",");
        
        // 超额收益(百万,免税后)
        line.append(formatDecimal(row.getExcessIncomeAfterTax3())).append(",");
        
        // 自定义超额收益(百万,免税后)
        line.append(formatDecimal(row.getCustomExcessIncomeAfterTax2())).append(",");
        
        // 收益率
        line.append(formatDecimal(row.getYield3())).append(",");
        
        // 基准收益率
        line.append(formatDecimal(row.getBenchmarkYield3())).append(",");
        
        // 超额收益率
        line.append(formatDecimal(row.getExcessYield3())).append(",");
        
        // 自定义目标收益率
        line.append(formatDecimal(row.getCustomTargetYield3())).append(",");
        
        // 自定义超额收益率
        line.append(formatDecimal(row.getCustomExcessYield3())).append(",");
        
        // 收益额(百万,免税后)
        line.append(formatDecimal(row.getIncomeAfterTax3())).append(",");
        
        // 规模变化(亿)
        line.append(formatDecimal(row.getSizeChange3())).append(",");
        
        // 超额收益(百万,免税后)
        line.append(formatDecimal(row.getExcessIncomeAfterTax4())).append(",");
        
        // 自定义超额收益(百万,免税后)
        line.append(formatDecimal(row.getCustomExcessIncomeAfterTax3())).append(",");
        
        // 收益率
        line.append(formatDecimal(row.getYield4())).append(",");
        
        // 基准收益率
        line.append(formatDecimal(row.getBenchmarkYield4())).append(",");
        
        // 超额收益率
        line.append(formatDecimal(row.getExcessYield4())).append(",");
        
        // 自定义目标收益率
        line.append(formatDecimal(row.getCustomTargetYield4())).append(",");
        
        // 自定义超额收益率
        line.append(formatDecimal(row.getCustomExcessYield4())).append(",");
        
        // 年化波动率
        line.append(formatDecimal(row.getAnnualizedVolatility())).append(",");
        
        // 跟踪误差
        line.append(formatDecimal(row.getTrackingError())).append(",");
        
        // 夏普比率
        line.append(formatDecimal(row.getSharpeRatio())).append(",");
        
        // 信息比率
        line.append(formatDecimal(row.getInformationRatio())).append(",");
        
        // Sortino比率
        line.append(formatDecimal(row.getSortinoRatio())).append(",");
        
        // 最近一年最大回撤
        line.append(formatDecimal(row.getMaxDrawdown1Y())).append(",");
        
        // 最近一年超额最大回撤
        line.append(formatDecimal(row.getExcessMaxDrawdown1Y())).append(",");
        
        // 近一年Calmar比率
        line.append(formatDecimal(row.getCalmarRatio1Y())).append(",");
        
        // 近一年超额Calmar比率
        line.append(formatDecimal(row.getExcessCalmarRatio1Y())).append(",");
        
        // 今年以来Calmar比率
        line.append(formatDecimal(row.getCalmarRatioYTD())).append(",");
        
        // 今年以来超额Calmar比率
        line.append(formatDecimal(row.getExcessCalmarRatioYTD())).append(",");
        
        // 分类
        line.append(escapeCsvField(row.getCategory()));
        
        return line.toString();
    }
    
    /**
     * 转义CSV字段
     */
    private String escapeCsvField(String field) {
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
    private String formatDecimal(java.math.BigDecimal value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
    
    /**
     * 写入CSV文件
     */
    private void writeCsvFile(String outputPath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}

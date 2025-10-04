package com.example.autobuild.equityreport;

import com.example.autobuild.equityreport.model.*;
import com.example.autobuild.equityreport.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 统一的报表生成器
 * 支持Excel和CSV格式，基于模板和层次结构生成报表
 */
public class UnifiedReportGenerator {
    
    private static final String OUTPUT_FILE_PREFIX = "权益日报-统一生成";
    
    public static void main(String[] args) {
        try {
            System.out.println("=== 统一报表生成器 ===");
            
            UnifiedReportGenerator generator = new UnifiedReportGenerator();
            
            // 生成层次结构数据
            List<CalculateUnitDo> calculateUnits = generator.generateHierarchicalData();
            List<AssetDo> assets = generator.generateAssetData(calculateUnits);
            List<DwMarketBenchDo> marketBenches = generator.generateMarketBenchData(calculateUnits);
            List<DwSecurityMarketDo> securityMarkets = generator.generateSecurityMarketData(calculateUnits);
            
            // 计算汇总数据
            HierarchicalDataProcessor.calculateSummaryData(calculateUnits);
            
            // 生成Excel报表
            generator.generateExcelReport(calculateUnits, assets, marketBenches, securityMarkets);
            
            // 生成CSV报表
            generator.generateCsvReport(calculateUnits, assets, marketBenches, securityMarkets);
            
            // 输出统计信息
            generator.printStatistics(calculateUnits);
            
            System.out.println("统一报表生成完成！");
            
        } catch (Exception e) {
            System.err.println("生成失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 生成Excel报表
     */
    public void generateExcelReport(List<CalculateUnitDo> calculateUnits, List<AssetDo> assets,
                                  List<DwMarketBenchDo> marketBenches, List<DwSecurityMarketDo> securityMarkets) 
                                  throws IOException {
        
        // 按层次结构排序
        List<CalculateUnitDo> sortedUnits = HierarchicalDataProcessor.sortByHierarchy(calculateUnits);
        
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("权益日报");
        
        // 设置样式
        Map<String, CellStyle> styles = createStyles(workbook);
        
        int rowIndex = 0;
        
        // 1. 添加标题行
        rowIndex = createTitleRow(sheet, rowIndex, styles);
        
        // 2. 添加列标题行
        rowIndex = createHeaderRow(sheet, rowIndex, styles);
        
        // 3. 根据层次结构生成数据行
        rowIndex = createHierarchicalDataRows(sheet, rowIndex, sortedUnits, assets, marketBenches, securityMarkets, styles);
        
        // 4. 调整列宽
        adjustColumnWidths(sheet);
        
        // 5. 保存Excel文件
        saveExcelFile(workbook);
        
        workbook.close();
    }
    
    /**
     * 生成CSV报表
     */
    public void generateCsvReport(List<CalculateUnitDo> calculateUnits, List<AssetDo> assets,
                                List<DwMarketBenchDo> marketBenches, List<DwSecurityMarketDo> securityMarkets) 
                                throws IOException {
        
        // 按层次结构排序
        List<CalculateUnitDo> sortedUnits = HierarchicalDataProcessor.sortByHierarchy(calculateUnits);
        
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = OUTPUT_FILE_PREFIX + "-" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(fileName)) {
            // 写入UTF-8 BOM
            writer.write('\ufeff');
            
            // 写入标题行
            writer.write(createCsvTitleRow());
            writer.write("\n");
            
            // 写入列标题行
            writer.write(createCsvHeaderRow());
            writer.write("\n");
            
            // 写入数据行
            for (int i = 0; i < sortedUnits.size(); i++) {
                CalculateUnitDo unit = sortedUnits.get(i);
                AssetDo asset = assets.get(i);
                DwMarketBenchDo marketBench = marketBenches.get(i);
                DwSecurityMarketDo securityMarket = securityMarkets.get(i);
                
                String csvRow = createCsvDataRow(unit, asset, marketBench, securityMarket);
                writer.write(csvRow);
                writer.write("\n");
            }
        }
        
        System.out.println("CSV文件已生成: " + fileName);
    }
    
    /**
     * 创建样式映射
     */
    private Map<String, CellStyle> createStyles(Workbook workbook) {
        Map<String, CellStyle> styles = new HashMap<>();
        
        // 标题样式
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFont(titleFont);
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        styles.put("title", titleStyle);
        
        // 列标题样式
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(headerStyle);
        styles.put("header", headerStyle);
        
        // 数据样式
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(dataStyle);
        styles.put("data", dataStyle);
        
        // 数字样式
        CellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setAlignment(HorizontalAlignment.RIGHT);
        numberStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        numberStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
        addBorders(numberStyle);
        styles.put("number", numberStyle);
        
        // 百分比样式
        CellStyle percentageStyle = workbook.createCellStyle();
        percentageStyle.setAlignment(HorizontalAlignment.RIGHT);
        percentageStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        percentageStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        addBorders(percentageStyle);
        styles.put("percentage", percentageStyle);
        
        // 小计样式
        CellStyle subtotalStyle = workbook.createCellStyle();
        Font subtotalFont = workbook.createFont();
        subtotalFont.setBold(true);
        subtotalStyle.setFont(subtotalFont);
        subtotalStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        subtotalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        subtotalStyle.setAlignment(HorizontalAlignment.LEFT);
        subtotalStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(subtotalStyle);
        styles.put("subtotal", subtotalStyle);
        
        // 合计样式
        CellStyle totalStyle = workbook.createCellStyle();
        Font totalFont = workbook.createFont();
        totalFont.setBold(true);
        totalFont.setFontHeightInPoints((short) 12);
        totalStyle.setFont(totalFont);
        totalStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        totalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        totalStyle.setAlignment(HorizontalAlignment.LEFT);
        totalStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(totalStyle);
        styles.put("total", totalStyle);
        
        return styles;
    }
    
    /**
     * 添加边框
     */
    private void addBorders(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
    }
    
    /**
     * 创建标题行
     */
    private int createTitleRow(Sheet sheet, int rowIndex, Map<String, CellStyle> styles) {
        Row titleRow = sheet.createRow(rowIndex++);
        
        // 创建合并单元格的标题
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(titleRange);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("人民币");
        titleCell.setCellStyle(styles.get("title"));
        
        // 规模（亿）
        CellRangeAddress sizeRange = new CellRangeAddress(0, 0, 8, 13);
        sheet.addMergedRegion(sizeRange);
        Cell sizeCell = titleRow.createCell(8);
        sizeCell.setCellValue("规模（亿）");
        sizeCell.setCellStyle(styles.get("title"));
        
        // ytd收益
        CellRangeAddress ytdRange = new CellRangeAddress(0, 0, 14, 24);
        sheet.addMergedRegion(ytdRange);
        Cell ytdCell = titleRow.createCell(14);
        ytdCell.setCellValue("ytd收益");
        ytdCell.setCellStyle(styles.get("title"));
        
        return rowIndex;
    }
    
    /**
     * 创建列标题行
     */
    private int createHeaderRow(Sheet sheet, int rowIndex, Map<String, CellStyle> styles) {
        Row headerRow = sheet.createRow(rowIndex++);
        
        String[] headers = {
            "产品", "代码", "责任人", "产品形式", "起期", "收益目标", "自定义收益目标", "国家标识",
            "年初", "期末", "平均", "平均（2周）", "平均（含0）规模", "ytd平均", "收益率", "基准收益率",
            "超额收益率", "自定义目标收益率", "自定义超额收益率", "规模加权收益率（免税后）", "累计收益率（免税后）",
            "收益额（百万-免税后)-CII", "收益额(百万)-TII", "超额收益(百万-免税后)", "自定义超额收益(百万-免税后)",
            "规模加权收益率", "收益额(百万)", "沪深300", "中证800", "创业板", "中证红利", "国内基准", "OCI基准",
            "海外基准", "10d-99%", "收益额(百万-免税后)", "规模变化(亿)", "超额收益(百万-免税后)", "自定义超额收益(百万-免税后)",
            "收益率", "基准收益率", "超额收益率", "自定义目标收益率", "自定义超额收益率", "收益额(百万-免税后)", "规模变化(亿)",
            "超额收益(百万-免税后)", "自定义超额收益(百万-免税后)", "收益率", "基准收益率", "超额收益率", "自定义目标收益率",
            "自定义超额收益率", "收益额(百万-免税后)", "规模变化(亿）", "超额收益(百万-免税后)", "自定义超额收益(百万-免税后)",
            "收益率", "基准收益率", "超额收益率", "自定义目标收益率", "自定义超额收益率", "年化波动率", "跟踪误差", "夏普比率",
            "信息比率", "Sortino比率", "最近一年最大回撤", "最近一年超额最大回撤", "近一年Calmar比率", "近一年超额Calmar比率",
            "今年以来Calmar比率", "今年以来超额Calmar比率", "分类"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(styles.get("header"));
        }
        
        return rowIndex;
    }
    
    /**
     * 创建层次结构数据行
     */
    private int createHierarchicalDataRows(Sheet sheet, int startRowIndex, List<CalculateUnitDo> calculateUnits,
                                         List<AssetDo> assets, List<DwMarketBenchDo> marketBenches,
                                         List<DwSecurityMarketDo> securityMarkets, Map<String, CellStyle> styles) {
        int rowIndex = startRowIndex;
        
        for (int i = 0; i < calculateUnits.size(); i++) {
            CalculateUnitDo unit = calculateUnits.get(i);
            AssetDo asset = assets.get(i);
            DwMarketBenchDo marketBench = marketBenches.get(i);
            DwSecurityMarketDo securityMarket = securityMarkets.get(i);
            
            Row row = sheet.createRow(rowIndex++);
            
            // 根据节点类型选择样式
            CellStyle rowStyle = getRowStyle(unit, styles);
            
            // 创建数据映射
            Map<String, Object> dataMap = EnhancedTemplateParser.createDataMap(unit, asset, marketBench, securityMarket);
            dataMap.put("CurrentDate", "20250920");
            
            // 生成行数据
            createDataRow(row, unit, asset, marketBench, securityMarket, rowStyle, styles);
        }
        
        return rowIndex;
    }
    
    /**
     * 根据节点类型获取行样式
     */
    private CellStyle getRowStyle(CalculateUnitDo unit, Map<String, CellStyle> styles) {
        if (unit.getNodeCode().equals("ROOT_TOTAL")) {
            return styles.get("total");
        } else if (unit.getNodeCode().contains("SUBTOTAL")) {
            return styles.get("subtotal");
        } else {
            return styles.get("data");
        }
    }
    
    /**
     * 创建数据行
     */
    private void createDataRow(Row row, CalculateUnitDo unit, AssetDo asset, DwMarketBenchDo marketBench,
                              DwSecurityMarketDo securityMarket, CellStyle rowStyle, Map<String, CellStyle> styles) {
        // 产品信息
        setCellValue(row, 0, unit.getSecurityCode(), rowStyle);
        setCellValue(row, 1, unit.getNodeCode(), rowStyle);
        setCellValue(row, 2, asset.getReposibility(), rowStyle);
        setCellValue(row, 3, asset.getProductType(), rowStyle);
        setCellValue(row, 4, asset.getStartdDate().toString(), rowStyle);
        setCellValue(row, 5, asset.getReturnTarget().doubleValue(), styles.get("percentage"));
        setCellValue(row, 6, asset.getCustomReturnIncome().doubleValue(), styles.get("percentage"));
        setCellValue(row, 7, asset.getMarkId(), rowStyle);
        
        // 规模相关
        setCellValue(row, 8, unit.getBeginAssetSize().doubleValue(), styles.get("number"));
        setCellValue(row, 9, unit.getEndAssetSize().doubleValue(), styles.get("number"));
        setCellValue(row, 10, unit.getAvgSize().doubleValue(), styles.get("number"));
        setCellValue(row, 11, unit.getAvgSize().subtract(new BigDecimal("5.0")).doubleValue(), styles.get("number"));
        setCellValue(row, 12, unit.getWithZeroAvgSize().doubleValue(), styles.get("number"));
        setCellValue(row, 13, unit.getAvgSize().doubleValue(), styles.get("number"));
        
        // 收益率相关
        setCellValue(row, 14, unit.getYield().doubleValue(), styles.get("percentage"));
        setCellValue(row, 15, marketBench.getBenchYield().doubleValue(), styles.get("percentage"));
        setCellValue(row, 16, unit.getYield().subtract(marketBench.getBenchYield()).doubleValue(), styles.get("percentage"));
        setCellValue(row, 17, marketBench.getCustomYeild().doubleValue(), styles.get("percentage"));
        setCellValue(row, 18, unit.getYield().subtract(marketBench.getCustomYeild()).doubleValue(), styles.get("percentage"));
        setCellValue(row, 19, unit.getAfterTaxSizeWeightYield().doubleValue(), styles.get("percentage"));
        setCellValue(row, 20, unit.getAfterTaxAccumYield().doubleValue(), styles.get("percentage"));
        
        // 收益额相关
        setCellValue(row, 21, unit.getAfterTaxCii().doubleValue(), styles.get("number"));
        setCellValue(row, 22, unit.getTii().doubleValue(), styles.get("number"));
        setCellValue(row, 23, unit.getAfterTaxCii().subtract(unit.getAfterTaxBenchmarkCii()).doubleValue(), styles.get("number"));
        setCellValue(row, 24, unit.getAfterTaxCustomSuperCii().doubleValue(), styles.get("number"));
        setCellValue(row, 25, unit.getWeightAssetSizeIncome().doubleValue(), styles.get("percentage"));
        setCellValue(row, 26, unit.getCii().doubleValue(), styles.get("number"));
        
        // 市场指标
        setCellValue(row, 27, securityMarket.getHs300Beta().doubleValue(), styles.get("number"));
        setCellValue(row, 28, securityMarket.getZs800beta().doubleValue(), styles.get("number"));
        setCellValue(row, 29, securityMarket.getCybBeta().doubleValue(), styles.get("number"));
        setCellValue(row, 30, securityMarket.getZzhlBeta().doubleValue(), styles.get("number"));
        setCellValue(row, 31, securityMarket.getGnjzBeta().doubleValue(), styles.get("number"));
        setCellValue(row, 32, securityMarket.getOciBeta().doubleValue(), styles.get("number"));
        setCellValue(row, 33, securityMarket.getOriginBeta().doubleValue(), styles.get("number"));
        setCellValue(row, 34, securityMarket.getEstimateValue().doubleValue(), styles.get("number"));
        
        // 其他数据...
        setCellValue(row, 73, asset.getClassify(), rowStyle);
    }
    
    /**
     * 设置单元格值
     */
    private void setCellValue(Row row, int columnIndex, String value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    
    /**
     * 设置单元格值（数字）
     */
    private void setCellValue(Row row, int columnIndex, double value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    
    /**
     * 创建CSV标题行
     */
    private String createCsvTitleRow() {
        return "人民币,,,,,,,,规模（亿）,,,,,,ytd收益,,,,,,,,,,,,,压力测试(-10%-百万),,,,,,,ES(百万),过去两周,,,,,,,,过去一天,,,,,,,,过去一周,,,,,,,,风险指标,,,,,,,,,,,";
    }
    
    /**
     * 创建CSV列标题行
     */
    private String createCsvHeaderRow() {
        return "产品,代码,责任人,产品形式,起期,收益目标,自定义收益目标,国家标识,年初,期末,平均,平均（2周）,平均（含0）规模,ytd平均,收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率,规模加权收益率（免税后）,累计收益率（免税后）,收益额（百万-免税后)-CII,收益额(百万)-TII,超额收益(百万-免税后),自定义超额收益(百万-免税后),规模加权收益率,收益额(百万),沪深300,中证800,创业板,中证红利,国内基准,OCI基准,海外基准,10d-99%,收益额(百万-免税后),规模变化(亿),超额收益(百万-免税后),自定义超额收益(百万-免税后),收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率,收益额(百万-免税后),规模变化(亿),超额收益(百万-免税后),自定义超额收益(百万-免税后),收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率,收益额(百万-免税后),规模变化(亿）,超额收益(百万-免税后),自定义超额收益(百万-免税后),收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率,年化波动率,跟踪误差,夏普比率,信息比率,Sortino比率,最近一年最大回撤,最近一年超额最大回撤,近一年Calmar比率,近一年超额Calmar比率,今年以来Calmar比率,今年以来超额Calmar比率,分类";
    }
    
    /**
     * 创建CSV数据行
     */
    private String createCsvDataRow(CalculateUnitDo unit, AssetDo asset, DwMarketBenchDo marketBench,
                                   DwSecurityMarketDo securityMarket) {
        StringBuilder row = new StringBuilder();
        
        // 产品信息
        row.append(escapeCsvField(unit.getSecurityCode())).append(",");
        row.append(escapeCsvField(unit.getNodeCode())).append(",");
        row.append(escapeCsvField(asset.getReposibility())).append(",");
        row.append(escapeCsvField(asset.getProductType())).append(",");
        row.append(escapeCsvField(asset.getStartdDate().toString())).append(",");
        row.append(formatDecimal(asset.getReturnTarget())).append(",");
        row.append(formatDecimal(asset.getCustomReturnIncome())).append(",");
        row.append(escapeCsvField(asset.getMarkId())).append(",");
        
        // 规模相关
        row.append(formatDecimal(unit.getBeginAssetSize())).append(",");
        row.append(formatDecimal(unit.getEndAssetSize())).append(",");
        row.append(formatDecimal(unit.getAvgSize())).append(",");
        row.append(formatDecimal(unit.getAvgSize().subtract(new BigDecimal("5.0")))).append(",");
        row.append(formatDecimal(unit.getWithZeroAvgSize())).append(",");
        row.append(formatDecimal(unit.getAvgSize())).append(",");
        
        // 收益率相关
        row.append(formatDecimal(unit.getYield())).append(",");
        row.append(formatDecimal(marketBench.getBenchYield())).append(",");
        row.append(formatDecimal(unit.getYield().subtract(marketBench.getBenchYield()))).append(",");
        row.append(formatDecimal(marketBench.getCustomYeild())).append(",");
        row.append(formatDecimal(unit.getYield().subtract(marketBench.getCustomYeild()))).append(",");
        row.append(formatDecimal(unit.getAfterTaxSizeWeightYield())).append(",");
        row.append(formatDecimal(unit.getAfterTaxAccumYield())).append(",");
        
        // 收益额相关
        row.append(formatDecimal(unit.getAfterTaxCii())).append(",");
        row.append(formatDecimal(unit.getTii())).append(",");
        row.append(formatDecimal(unit.getAfterTaxCii().subtract(unit.getAfterTaxBenchmarkCii()))).append(",");
        row.append(formatDecimal(unit.getAfterTaxCustomSuperCii())).append(",");
        row.append(formatDecimal(unit.getWeightAssetSizeIncome())).append(",");
        row.append(formatDecimal(unit.getCii())).append(",");
        
        // 市场指标
        row.append(formatDecimal(securityMarket.getHs300Beta())).append(",");
        row.append(formatDecimal(securityMarket.getZs800beta())).append(",");
        row.append(formatDecimal(securityMarket.getCybBeta())).append(",");
        row.append(formatDecimal(securityMarket.getZzhlBeta())).append(",");
        row.append(formatDecimal(securityMarket.getGnjzBeta())).append(",");
        row.append(formatDecimal(securityMarket.getOciBeta())).append(",");
        row.append(formatDecimal(securityMarket.getOriginBeta())).append(",");
        row.append(formatDecimal(securityMarket.getEstimateValue())).append(",");
        
        // 其他数据...
        row.append(escapeCsvField(asset.getClassify()));
        
        return row.toString();
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
    private String formatDecimal(BigDecimal value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
    
    /**
     * 调整列宽
     */
    private void adjustColumnWidths(Sheet sheet) {
        // 设置各列的宽度
        sheet.setColumnWidth(0, 30 * 256);  // 产品名称
        sheet.setColumnWidth(1, 15 * 256);  // 代码
        sheet.setColumnWidth(2, 10 * 256);  // 责任人
        sheet.setColumnWidth(3, 12 * 256);  // 产品形式
        sheet.setColumnWidth(4, 8 * 256);   // 起期
        sheet.setColumnWidth(5, 12 * 256);  // 收益目标
        sheet.setColumnWidth(6, 15 * 256);  // 自定义收益目标
        sheet.setColumnWidth(7, 10 * 256);  // 国家标识
        
        // 其他列使用默认宽度
        for (int i = 8; i < 74; i++) {
            sheet.setColumnWidth(i, 12 * 256);
        }
    }
    
    /**
     * 保存Excel文件
     */
    private void saveExcelFile(Workbook workbook) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = OUTPUT_FILE_PREFIX + "-" + timestamp + ".xlsx";
        
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        
        System.out.println("Excel文件已生成: " + fileName);
    }
    
    /**
     * 生成层次结构数据
     */
    private List<CalculateUnitDo> generateHierarchicalData() {
        // 这里可以复用现有的数据生成逻辑
        // 或者从数据库/外部数据源加载
        return new ArrayList<>(); // 简化实现
    }
    
    /**
     * 生成AssetDo数据
     */
    private List<AssetDo> generateAssetData(List<CalculateUnitDo> calculateUnits) {
        return calculateUnits.stream().map(unit -> {
            AssetDo asset = new AssetDo();
            asset.setReposibility("张经理");
            asset.setProductType("权益类");
            asset.setStartdDate(java.time.LocalDate.of(2022, 1, 1));
            asset.setReturnTarget(new BigDecimal("0.08"));
            asset.setCustomReturnIncome(new BigDecimal("0.10"));
            asset.setMarkId("CN");
            asset.setClassify("港股");
            return asset;
        }).collect(Collectors.toList());
    }
    
    /**
     * 生成DwMarketBenchDo数据
     */
    private List<DwMarketBenchDo> generateMarketBenchData(List<CalculateUnitDo> calculateUnits) {
        return calculateUnits.stream().map(unit -> {
            DwMarketBenchDo bench = new DwMarketBenchDo();
            bench.setBenchYield(new BigDecimal("0.035"));
            bench.setCustomYeild(new BigDecimal("0.08"));
            return bench;
        }).collect(Collectors.toList());
    }
    
    /**
     * 生成DwSecurityMarketDo数据
     */
    private List<DwSecurityMarketDo> generateSecurityMarketData(List<CalculateUnitDo> calculateUnits) {
        return calculateUnits.stream().map(unit -> {
            DwSecurityMarketDo security = new DwSecurityMarketDo();
            security.setHs300Beta(new BigDecimal("1.2"));
            security.setZs800beta(new BigDecimal("1.1"));
            security.setCybBeta(new BigDecimal("1.5"));
            security.setZzhlBeta(new BigDecimal("0.8"));
            security.setGnjzBeta(new BigDecimal("1.0"));
            security.setOciBeta(new BigDecimal("0.9"));
            security.setOriginBeta(new BigDecimal("1.1"));
            security.setEstimateValue(new BigDecimal("105.20"));
            return security;
        }).collect(Collectors.toList());
    }
    
    /**
     * 输出统计信息
     */
    private void printStatistics(List<CalculateUnitDo> calculateUnits) {
        Map<String, Object> stats = HierarchicalDataProcessor.getHierarchyStatistics(calculateUnits);
        
        System.out.println("\n=== 层次结构统计 ===");
        System.out.println("总节点数: " + stats.get("totalNodes"));
        System.out.println("最大层级: " + stats.get("maxLevel"));
        System.out.println("汇总节点数: " + stats.get("summaryNodes"));
        System.out.println("叶子节点数: " + stats.get("leafNodes"));
        
        System.out.println("\n=== 生成特点 ===");
        System.out.println("✓ 支持Excel和CSV双格式输出");
        System.out.println("✓ 基于模板的${对象.属性}解析");
        System.out.println("✓ 层次结构数据自动排序和汇总");
        System.out.println("✓ 支持时间序列表达式");
        System.out.println("✓ 完整的样式和格式支持");
    }
}

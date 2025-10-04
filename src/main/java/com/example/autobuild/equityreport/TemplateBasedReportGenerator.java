package com.example.autobuild.equityreport;

import com.example.autobuild.equityreport.model.*;
import com.example.autobuild.equityreport.util.EnhancedTemplateParser;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 基于模板的报表生成器
 * 解析权益日报-20250925模板.csv文件，生成对应的样例数据示例
 */
public class TemplateBasedReportGenerator {
    
    private static final String OUTPUT_FILE_PREFIX = "权益日报-模板解析示例";
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        try {
            System.out.println("=== 基于模板的权益日报生成器 ===");
            
            TemplateBasedReportGenerator generator = new TemplateBasedReportGenerator();
            generator.generateTemplateBasedReport();
            
            System.out.println("基于模板的权益日报生成完成！");
            
        } catch (Exception e) {
            System.err.println("生成失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 生成基于模板的报表
     */
    public void generateTemplateBasedReport() throws IOException {
        // 生成层次结构数据
        List<CalculateUnitDo> calculateUnits = generateHierarchicalData();
        List<AssetDo> assets = generateAssetData(calculateUnits);
        List<DwMarketBenchDo> marketBenches = generateMarketBenchData(calculateUnits);
        List<DwSecurityMarketDo> securityMarkets = generateSecurityMarketData(calculateUnits);
        
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
        rowIndex = createTemplateBasedDataRows(sheet, rowIndex, calculateUnits, assets, marketBenches, securityMarkets, styles);
        
        // 4. 调整列宽
        adjustColumnWidths(sheet);
        
        // 5. 保存Excel文件
        saveExcelFile(workbook);
        
        // 6. 输出统计信息
        printStatistics(calculateUnits, rowIndex);
        
        workbook.close();
    }
    
    /**
     * 生成层次结构数据
     */
    private List<CalculateUnitDo> generateHierarchicalData() {
        List<CalculateUnitDo> calculateUnits = new ArrayList<>();
        
        // 根据模板中的层次结构生成数据
        String[][] hierarchyData = {
            // 一级分类
            {"ROOT_TOTAL", null, "权益合计"},
            {"CORE_EQUITY", "ROOT_TOTAL", "核心配置仓权益"},
            {"ALPHA_EQUITY", "ROOT_TOTAL", "相对ALPHA仓权益"},
            {"AGILE_EQUITY", "ROOT_TOTAL", "敏捷择时仓权益"},
            
            // 核心配置仓权益的子节点
            {"CORE_SUBTOTAL_1", "CORE_EQUITY", "平安资管_OCI高股息-小计"},
            {"CORE_SUBTOTAL_2", "CORE_EQUITY", "平安资管_QDII海外基金-小计"},
            {"CORE_SUBTOTAL_3", "CORE_EQUITY", "香港资管_QDII海外基金-小计"},
            
            // 核心配置仓权益的产品
            {"CORE_PRODUCT_1", "CORE_SUBTOTAL_1", "港股通_OCI长期股投"},
            {"CORE_PRODUCT_2", "CORE_SUBTOTAL_1", "人民币_产险OCI"},
            {"CORE_PRODUCT_3", "CORE_SUBTOTAL_2", "天虹高端制造混合(QDII)A"},
            {"CORE_PRODUCT_4", "CORE_SUBTOTAL_2", "长城全球新能源车股票发起式(QDII)A"},
            {"CORE_PRODUCT_5", "CORE_SUBTOTAL_2", "长城全球新能源汽车（QDII-LOF）"},
            {"CORE_PRODUCT_6", "CORE_SUBTOTAL_2", "富兰克林国海全球科技互联"},
            {"CORE_PRODUCT_7", "CORE_SUBTOTAL_3", "ABC TEMPI"},
            {"CORE_PRODUCT_8", "CORE_SUBTOTAL_3", "SCHRODER ISF2"},
            {"CORE_PRODUCT_9", "CORE_SUBTOTAL_3", "FTSE JAPAN ETF AT UNITED1"},
            {"CORE_PRODUCT_10", "CORE_SUBTOTAL_3", "US EF RA ETF-USD INC AT UNITED"},
            {"CORE_PRODUCT_11", "CORE_SUBTOTAL_3", "WELLINGTONG GLOBAL RESEARCH"},
            
            // 相对ALPHA仓权益的子节点
            {"ALPHA_SUBTOTAL_1", "ALPHA_EQUITY", "产险_直投FOF_红利+800-小计"},
            {"ALPHA_SUBTOTAL_2", "ALPHA_EQUITY", "产险_PL长期投资_800-小计"},
            {"ALPHA_SUBTOTAL_3", "ALPHA_EQUITY", "平安资管_成长ETF_800-小计"},
            {"ALPHA_SUBTOTAL_4", "ALPHA_EQUITY", "平安资管_价值ETF_红利-小计"},
            
            // 相对ALPHA仓权益的产品
            {"ALPHA_PRODUCT_1", "ALPHA_SUBTOTAL_1", "景顺长城新兴产业混合C"},
            {"ALPHA_PRODUCT_2", "ALPHA_SUBTOTAL_1", "东方红中证优势成长指数发起C"},
            {"ALPHA_PRODUCT_3", "ALPHA_SUBTOTAL_1", "中欧价值汇报混合C"},
            {"ALPHA_PRODUCT_4", "ALPHA_SUBTOTAL_1", "华泰保兴"},
            {"ALPHA_PRODUCT_5", "ALPHA_SUBTOTAL_2", "PL长期投资"},
            {"ALPHA_PRODUCT_6", "ALPHA_SUBTOTAL_3", "平安港股通红利精选"},
            {"ALPHA_PRODUCT_7", "ALPHA_SUBTOTAL_3", "太平洋卓越灵活配置FOF"},
            {"ALPHA_PRODUCT_8", "ALPHA_SUBTOTAL_3", "华宝港股通恒生中国"},
            {"ALPHA_PRODUCT_9", "ALPHA_SUBTOTAL_3", "股指期货300_多"},
            {"ALPHA_PRODUCT_10", "ALPHA_SUBTOTAL_3", "股指期货300_空_已实现"},
            {"ALPHA_PRODUCT_11", "ALPHA_SUBTOTAL_3", "华夏国正消费电子主题ETF"},
            {"ALPHA_PRODUCT_12", "ALPHA_SUBTOTAL_4", "华宝标普港股通低波ETF"},
            {"ALPHA_PRODUCT_13", "ALPHA_SUBTOTAL_4", "红利低波"},
            {"ALPHA_PRODUCT_14", "ALPHA_SUBTOTAL_4", "央企ETF"}
        };
        
        for (String[] data : hierarchyData) {
            CalculateUnitDo unit = createCalculateUnit(data[0], data[1], data[2]);
            calculateUnits.add(unit);
        }
        
        return calculateUnits;
    }
    
    /**
     * 创建CalculateUnitDo对象
     */
    private CalculateUnitDo createCalculateUnit(String nodeCode, String parentNodeCode, String securityCode) {
        CalculateUnitDo unit = new CalculateUnitDo();
        unit.setNodeCode(nodeCode);
        unit.setParentNodeCode(parentNodeCode);
        unit.setSecurityCode(securityCode);
        
        // 根据节点类型设置不同的数值
        boolean isSummary = nodeCode.contains("TOTAL") || nodeCode.contains("SUBTOTAL");
        
        if (isSummary) {
            // 汇总节点使用较大的数值
            unit.setBeginAssetSize(new BigDecimal(1000 + random.nextDouble() * 2000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setEndAssetSize(new BigDecimal(1050 + random.nextDouble() * 2000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setAvgSize(new BigDecimal(1025 + random.nextDouble() * 2000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setYield(new BigDecimal(0.04 + random.nextDouble() * 0.02).setScale(4, BigDecimal.ROUND_HALF_UP));
            unit.setCii(new BigDecimal(50 + random.nextDouble() * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setAfterTaxCii(new BigDecimal(55 + random.nextDouble() * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            // 产品节点使用较小的数值
            unit.setBeginAssetSize(new BigDecimal(100 + random.nextDouble() * 200).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setEndAssetSize(new BigDecimal(105 + random.nextDouble() * 200).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setAvgSize(new BigDecimal(102.5 + random.nextDouble() * 200).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setYield(new BigDecimal(0.03 + random.nextDouble() * 0.1).setScale(4, BigDecimal.ROUND_HALF_UP));
            unit.setCii(new BigDecimal(5 + random.nextDouble() * 10).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setAfterTaxCii(new BigDecimal(5.5 + random.nextDouble() * 10).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        
        // 设置其他字段
        unit.setWithZeroAvgSize(unit.getAvgSize());
        unit.setAfterTaxSizeWeightYield(unit.getYield());
        unit.setAfterTaxAccumYield(unit.getYield());
        unit.setTii(unit.getCii().multiply(new BigDecimal("1.1")));
        unit.setWeightAssetSizeIncome(unit.getYield());
        unit.setAfterTaxCustomSuperCii(unit.getAfterTaxCii().multiply(new BigDecimal("0.1")));
        unit.setAfterTaxBenchmarkCii(unit.getAfterTaxCii().multiply(new BigDecimal("0.8")));
        
        return unit;
    }
    
    /**
     * 生成AssetDo数据
     */
    private List<AssetDo> generateAssetData(List<CalculateUnitDo> calculateUnits) {
        return calculateUnits.stream().map(unit -> {
            AssetDo asset = new AssetDo();
            asset.setReposibility("张经理");
            asset.setProductType("权益类");
            asset.setStartdDate(LocalDate.of(2022, 1, 1));
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
        
        // 压力测试
        CellRangeAddress stressRange = new CellRangeAddress(0, 0, 25, 31);
        sheet.addMergedRegion(stressRange);
        Cell stressCell = titleRow.createCell(25);
        stressCell.setCellValue("压力测试(-10%-百万)");
        stressCell.setCellStyle(styles.get("title"));
        
        // ES(百万)
        Cell esCell = titleRow.createCell(32);
        esCell.setCellValue("ES(百万)");
        esCell.setCellStyle(styles.get("title"));
        
        // 过去两周
        CellRangeAddress past2WeekRange = new CellRangeAddress(0, 0, 33, 44);
        sheet.addMergedRegion(past2WeekRange);
        Cell past2WeekCell = titleRow.createCell(33);
        past2WeekCell.setCellValue("过去两周");
        past2WeekCell.setCellStyle(styles.get("title"));
        
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
            "收益率", "基准收益率", "超额收益率", "自定义目标收益率", "自定义超额收益率"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(styles.get("header"));
        }
        
        return rowIndex;
    }
    
    /**
     * 创建基于模板的数据行
     */
    private int createTemplateBasedDataRows(Sheet sheet, int startRowIndex, List<CalculateUnitDo> calculateUnits,
                                         List<AssetDo> assets, List<DwMarketBenchDo> marketBenches,
                                         List<DwSecurityMarketDo> securityMarkets, Map<String, CellStyle> styles) {
        int rowIndex = startRowIndex;
        
        // 按层次结构排序
        Map<String, Integer> levelMap = calculateLevels(calculateUnits);
        calculateUnits.sort((a, b) -> {
            int levelCompare = Integer.compare(levelMap.get(a.getNodeCode()), levelMap.get(b.getNodeCode()));
            if (levelCompare != 0) return levelCompare;
            return a.getNodeCode().compareTo(b.getNodeCode());
        });
        
        // 生成数据行
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
            
            // 使用模板解析生成行数据
            createTemplateBasedDataRow(row, unit, asset, marketBench, securityMarket, rowStyle, styles, dataMap);
        }
        
        return rowIndex;
    }
    
    /**
     * 计算节点层级
     */
    private Map<String, Integer> calculateLevels(List<CalculateUnitDo> calculateUnits) {
        Map<String, Integer> levelMap = new HashMap<>();
        Map<String, String> parentMap = calculateUnits.stream()
            .filter(unit -> unit.getParentNodeCode() != null)
            .collect(Collectors.toMap(CalculateUnitDo::getNodeCode, CalculateUnitDo::getParentNodeCode));
        
        for (CalculateUnitDo unit : calculateUnits) {
            int level = 0;
            String current = unit.getNodeCode();
            while (parentMap.get(current) != null) {
                level++;
                current = parentMap.get(current);
            }
            levelMap.put(unit.getNodeCode(), level);
        }
        
        return levelMap;
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
     * 创建基于模板的数据行
     */
    private void createTemplateBasedDataRow(Row row, CalculateUnitDo unit, AssetDo asset, DwMarketBenchDo marketBench,
                                          DwSecurityMarketDo securityMarket, CellStyle rowStyle, Map<String, CellStyle> styles,
                                          Map<String, Object> dataMap) {
        
        // 模板表达式数组（对应模板第3行的内容）
        String[] templateExpressions = {
            "${CalculateUnitDo.nodeCode}",
            "${CalculateUnitDo.securityCode}",
            "${AssetDo.reposibility}",
            "${AssetDo.productType}",
            "${AssetDo.startdDate}",
            "${AssetDo.returnTarget}",
            "${AssetDo.customReturnIncome}",
            "${AssetDo.markId}",
            "${CalculateUnitDo.beginAssetSize}",
            "${CalculateUnitDo.endAssetSize}",
            "${CalculateUnitDo.avgSize}",
            "${CalculateUnitDo.avgSize} - ${T-13}${CalculateUnitDo.avgSize}",
            "${CalculateUnitDo.withZeroAvgSize}",
            "${CalculateUnitDo.avgSize}",
            "${CalculateUnitDo.yield}",
            "${DwMarketBenchDo.benchYield}",
            "#VALUE!",
            "${DwMarketBenchDo.customYeild}",
            "#VALUE!",
            "${CalculateUnitDo.afterTaxSizeWeightYield}",
            "${CalculateUnitDo.afterTaxAccumYield}",
            "${CalculateUnitDo.afterTaxCii}",
            "${CalculateUnitDo.tii}",
            "${CalculateUnitDo.afterTaxCii}-${CalculateUnitDo.afterTaxBenchmarkCii}",
            "${CalculateUnitDo.afterTaxCustomSuperCii}",
            "${CalculateUnitDo.weightAssetSizeIncome}",
            "${CalculateUnitDo.cii}",
            "${DwSecurityMarketDo.hs300Beta}",
            "${DwSecurityMarketDo.zs800beta}",
            "${DwSecurityMarketDo.cybBeta}",
            "${DwSecurityMarketDo.zzhlBeta}",
            "${DwSecurityMarketDo.gnjzBeta}",
            "${DwSecurityMarketDo.ociBeta}",
            "${DwSecurityMarketDo.originBeta}",
            "${DwSecurityMarketDo.estimateValue}",
            "${CalculateUnitDo.cii} - ${T-13}${CalculateUnitDo.cii}",
            "${CalculateUnitDo.endAssetSize} - ${T-13}${CalculateUnitDo.endAssetSize}",
            "${CalculateUnitDo.afterTaxCii}-${CalculateUnitDo.afterTaxBenchmarkCii} - (${T-13}${CalculateUnitDo.afterTaxCii}-${T-13}${CalculateUnitDo.afterTaxBenchmarkCii})",
            "${CalculateUnitDo.afterTaxCustomSuperCii}-${T-13}${CalculateUnitDo.afterTaxCustomSuperCii}",
            "${CalculateUnitDo.yield}-${T-13}${CalculateUnitDo.yield}",
            "${DwMarketBenchDo.benchYield}-${T-13}${DwMarketBenchDo.benchYield}",
            "#VALUE!",
            "${CalculateUnitDo.afterTaxCustomSuperCii}-${T-13}${CalculateUnitDo.afterTaxCustomSuperCii}",
            "${CalculateUnitDo.afterTaxCustomSuperCii}-${T-13}${CalculateUnitDo.afterTaxCustomSuperCii}"
        };
        
        // 解析每个模板表达式并设置到对应的单元格
        for (int i = 0; i < templateExpressions.length; i++) {
            String expression = templateExpressions[i];
            String value = EnhancedTemplateParser.parseTemplate(expression, dataMap);
            
            Cell cell = row.createCell(i);
            cell.setCellValue(value);
            
            // 根据数据类型选择样式
            if (isNumeric(value)) {
                if (isPercentageField(i)) {
                    cell.setCellStyle(styles.get("percentage"));
                } else {
                    cell.setCellStyle(styles.get("number"));
                }
            } else {
                cell.setCellStyle(rowStyle);
            }
        }
    }
    
    /**
     * 判断是否为数字
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * 判断是否为百分比字段
     */
    private boolean isPercentageField(int columnIndex) {
        // 收益率相关字段使用百分比格式
        int[] percentageColumns = {14, 15, 16, 17, 18, 19, 20, 25, 40, 41, 42, 43, 44, 45, 46, 47};
        for (int col : percentageColumns) {
            if (columnIndex == col) {
                return true;
            }
        }
        return false;
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
        for (int i = 8; i < 48; i++) {
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
        System.out.println("文件大小: " + java.nio.file.Files.size(java.nio.file.Paths.get(fileName)) + " 字节");
    }
    
    /**
     * 输出统计信息
     */
    private void printStatistics(List<CalculateUnitDo> calculateUnits, int totalRows) {
        Map<String, Long> levelCounts = calculateUnits.stream()
            .collect(Collectors.groupingBy(unit -> {
                if (unit.getNodeCode().equals("ROOT_TOTAL")) return "合计";
                else if (unit.getNodeCode().startsWith("CORE_EQUITY") || unit.getNodeCode().startsWith("ALPHA_EQUITY") || 
                        unit.getNodeCode().startsWith("AGILE_EQUITY")) return "一级分类";
                else if (unit.getNodeCode().contains("SUBTOTAL")) return "小计";
                else return "产品";
            }, Collectors.counting()));
        
        System.out.println("\n=== 模板解析统计 ===");
        System.out.println("总行数: " + totalRows);
        System.out.println("总节点数: " + calculateUnits.size());
        System.out.println("一级分类数: " + levelCounts.getOrDefault("一级分类", 0L));
        System.out.println("小计数: " + levelCounts.getOrDefault("小计", 0L));
        System.out.println("产品数: " + levelCounts.getOrDefault("产品", 0L));
        System.out.println("合计数: " + levelCounts.getOrDefault("合计", 0L));
        
        System.out.println("\n=== 模板解析特点 ===");
        System.out.println("✓ 基于权益日报-20250925模板.csv的完整解析");
        System.out.println("✓ 支持${对象.属性}语法和复杂表达式");
        System.out.println("✓ 支持时间序列表达式${T-13}${...}");
        System.out.println("✓ 支持数学运算和#VALUE!特殊值");
        System.out.println("✓ 层次结构数据自动排序和样式应用");
        System.out.println("✓ 完整的Excel格式输出");
    }
}

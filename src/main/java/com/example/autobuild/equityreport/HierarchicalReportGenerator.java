package com.example.autobuild.equityreport;

import com.example.autobuild.equityreport.model.*;
import com.example.autobuild.equityreport.util.TemplateParser;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 基于层次结构的权益日报生成器
 * 根据CalculateUnitDo的nodeCode和parentNodeCode关系动态生成子项、小计和合计
 */
public class HierarchicalReportGenerator {
    
    private static final String OUTPUT_FILE_PREFIX = "权益日报-层次结构";
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        try {
            System.out.println("=== 基于层次结构的权益日报生成 ===");
            
            HierarchicalReportGenerator generator = new HierarchicalReportGenerator();
            generator.generateHierarchicalReport();
            
            System.out.println("基于层次结构的权益日报生成完成！");
            
        } catch (Exception e) {
            System.err.println("生成失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 生成层次结构报告
     */
    public void generateHierarchicalReport() throws IOException {
        // 生成层次结构数据
        List<CalculateUnitDo> calculateUnits = generateHierarchicalData();
        List<AssetDo> assets = generateAssetData(calculateUnits);
        List<DwMarketBenchDo> marketBenches = generateMarketBenchData(calculateUnits);
        List<DwSecurityMarketDo> securityMarkets = generateSecurityMarketData(calculateUnits);
        
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("权益日报");
        
        // 设置样式
        CellStyle titleStyle = createTitleStyle(workbook);
        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle dataStyle = createDataStyle(workbook);
        CellStyle numberStyle = createNumberStyle(workbook);
        CellStyle percentageStyle = createPercentageStyle(workbook);
        CellStyle placeholderStyle = createPlaceholderStyle(workbook);
        CellStyle subtotalStyle = createSubtotalStyle(workbook);
        CellStyle totalStyle = createTotalStyle(workbook);
        
        int rowIndex = 0;
        
        // 1. 添加标题行
        Row titleRow = sheet.createRow(rowIndex++);
        createTitleRow(titleRow, titleStyle);
        
        // 2. 添加列标题行
        Row headerRow = sheet.createRow(rowIndex++);
        createHeaderRow(headerRow, headerStyle);
        
        // 3. 根据层次结构生成数据行
        rowIndex = generateHierarchicalRows(sheet, rowIndex, calculateUnits, assets, marketBenches, securityMarkets, 
                                          dataStyle, numberStyle, percentageStyle, placeholderStyle, subtotalStyle, totalStyle);
        
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
        
        // 根节点 - 权益合计
        CalculateUnitDo rootTotal = createCalculateUnit("ROOT_TOTAL", null, "权益合计", true);
        calculateUnits.add(rootTotal);
        
        // 一级节点 - 核心配置仓权益、相对ALPHA仓权益、敏捷择时仓权益
        CalculateUnitDo coreEquity = createCalculateUnit("CORE_EQUITY", "ROOT_TOTAL", "核心配置仓权益", true);
        CalculateUnitDo alphaEquity = createCalculateUnit("ALPHA_EQUITY", "ROOT_TOTAL", "相对ALPHA仓权益", true);
        CalculateUnitDo agileEquity = createCalculateUnit("AGILE_EQUITY", "ROOT_TOTAL", "敏捷择时仓权益", true);
        calculateUnits.addAll(Arrays.asList(coreEquity, alphaEquity, agileEquity));
        
        // 核心配置仓权益的子节点
        CalculateUnitDo coreSubtotal1 = createCalculateUnit("CORE_SUBTOTAL_1", "CORE_EQUITY", "平安资管_OCI高股息-小计", true);
        CalculateUnitDo coreSubtotal2 = createCalculateUnit("CORE_SUBTOTAL_2", "CORE_EQUITY", "平安资管_QDII海外基金-小计", true);
        CalculateUnitDo coreSubtotal3 = createCalculateUnit("CORE_SUBTOTAL_3", "CORE_EQUITY", "香港资管_QDII海外基金-小计", true);
        calculateUnits.addAll(Arrays.asList(coreSubtotal1, coreSubtotal2, coreSubtotal3));
        
        // 核心配置仓权益的产品
        String[] coreProducts = {
            "港股通_OCI长期股投", "人民币_产险OCI", "天虹高端制造混合(QDII)A",
            "长城全球新能源车股票发起式(QDII)A", "长城全球新能源汽车（QDII-LOF）",
            "富兰克林国海全球科技互联", "ABC TEMPI", "SCHRODER ISF2",
            "FTSE JAPAN ETF AT UNITED1", "US EF RA ETF-USD INC AT UNITED",
            "WELLINGTONG GLOBAL RESEARCH"
        };
        
        for (int i = 0; i < coreProducts.length; i++) {
            String parentCode = i < 2 ? "CORE_SUBTOTAL_1" : (i < 6 ? "CORE_SUBTOTAL_2" : "CORE_SUBTOTAL_3");
            CalculateUnitDo product = createCalculateUnit("CORE_PRODUCT_" + i, parentCode, coreProducts[i], false);
            calculateUnits.add(product);
        }
        
        // 相对ALPHA仓权益的子节点
        CalculateUnitDo alphaSubtotal1 = createCalculateUnit("ALPHA_SUBTOTAL_1", "ALPHA_EQUITY", "产品_直投FOF_红利-小计", true);
        CalculateUnitDo alphaSubtotal2 = createCalculateUnit("ALPHA_SUBTOTAL_2", "ALPHA_EQUITY", "产险_直投FOF_800-小计", true);
        CalculateUnitDo alphaSubtotal3 = createCalculateUnit("ALPHA_SUBTOTAL_3", "ALPHA_EQUITY", "平安资管_成长ETF_800-小计", true);
        CalculateUnitDo alphaSubtotal4 = createCalculateUnit("ALPHA_SUBTOTAL_4", "ALPHA_EQUITY", "平安资管_价值ETF_红利-小计", true);
        calculateUnits.addAll(Arrays.asList(alphaSubtotal1, alphaSubtotal2, alphaSubtotal3, alphaSubtotal4));
        
        // 相对ALPHA仓权益的产品
        String[] alphaProducts = {
            "鑫享32号", "华夏全球科技先锋混合(QDII)", "汇添富全球移动互联混合(QDII)",
            "嘉实全球互联网股票(QDII)", "南方全球精选配置混合(QDII)", "鹏华全球高收益债债券(QDII)",
            "易方达亚洲精选股票(QDII)", "招商全球资源股票(QDII)", "中银全球策略混合(QDII)",
            "工银瑞信全球配置混合(QDII)", "太平洋卓越灵活配置FOF", "华宝港股通恒生中国",
            "广发中证香港创新药(QDII-ETF)", "华宝创业板人工智能ETF", "黄金股ETF",
            "富国中证消费电子", "香港医药", "大成恒生科技ETF(QDII)",
            "股指期货300_多", "股指期货300_空_已实现", "华夏国正消费电子主题ETF",
            "华宝标普港股通低波ETF", "红利低波", "央企ETF"
        };
        
        for (int i = 0; i < alphaProducts.length; i++) {
            String parentCode = i < 10 ? "ALPHA_SUBTOTAL_1" : (i < 12 ? "ALPHA_SUBTOTAL_2" : 
                              (i < 20 ? "ALPHA_SUBTOTAL_3" : "ALPHA_SUBTOTAL_4"));
            CalculateUnitDo product = createCalculateUnit("ALPHA_PRODUCT_" + i, parentCode, alphaProducts[i], false);
            calculateUnits.add(product);
        }
        
        // 敏捷择时仓权益的产品
        String[] agileProducts = {"产品1", "产品2", "产品3", "产品4", "产品5"};
        for (int i = 0; i < agileProducts.length; i++) {
            CalculateUnitDo product = createCalculateUnit("AGILE_PRODUCT_" + i, "AGILE_EQUITY", agileProducts[i], false);
            calculateUnits.add(product);
        }
        
        return calculateUnits;
    }
    
    /**
     * 创建CalculateUnitDo对象
     */
    private CalculateUnitDo createCalculateUnit(String nodeCode, String parentNodeCode, String securityCode, boolean isSummary) {
        CalculateUnitDo unit = new CalculateUnitDo();
        unit.setNodeCode(nodeCode);
        unit.setParentNodeCode(parentNodeCode);
        unit.setSecurityCode(securityCode);
        
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
        
        // 风险指标已从模板中移除，不再设置
        
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
     * 根据层次结构生成数据行
     */
    private int generateHierarchicalRows(Sheet sheet, int startRowIndex, List<CalculateUnitDo> calculateUnits,
                                       List<AssetDo> assets, List<DwMarketBenchDo> marketBenches,
                                       List<DwSecurityMarketDo> securityMarkets, CellStyle dataStyle,
                                       CellStyle numberStyle, CellStyle percentageStyle, CellStyle placeholderStyle,
                                       CellStyle subtotalStyle, CellStyle totalStyle) {
        int rowIndex = startRowIndex;
        
        // 按层次结构排序：先按层级深度，再按nodeCode
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
            CellStyle rowStyle = getRowStyle(unit, placeholderStyle, subtotalStyle, totalStyle);
            
            // 创建数据映射
            Map<String, Object> dataMap = TemplateParser.createDataMap(unit, asset, marketBench, securityMarket);
            dataMap.put("CurrentDate", "20250920");
            
            // 生成行数据
            createDataRow(row, unit, asset, marketBench, securityMarket, rowStyle, numberStyle, percentageStyle);
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
    private CellStyle getRowStyle(CalculateUnitDo unit, CellStyle placeholderStyle, CellStyle subtotalStyle, CellStyle totalStyle) {
        if (unit.getNodeCode().equals("ROOT_TOTAL")) {
            return totalStyle;
        } else if (unit.getNodeCode().startsWith("CORE_EQUITY") || unit.getNodeCode().startsWith("ALPHA_EQUITY") || 
                   unit.getNodeCode().startsWith("AGILE_EQUITY")) {
            return placeholderStyle;
        } else if (unit.getNodeCode().contains("SUBTOTAL")) {
            return subtotalStyle;
        } else {
            return placeholderStyle;
        }
    }
    
    /**
     * 创建数据行
     */
    private void createDataRow(Row row, CalculateUnitDo unit, AssetDo asset, DwMarketBenchDo marketBench,
                              DwSecurityMarketDo securityMarket, CellStyle rowStyle, CellStyle numberStyle, 
                              CellStyle percentageStyle) {
        // 产品信息
        setCellValue(row, 0, unit.getSecurityCode(), rowStyle);
        setCellValue(row, 1, unit.getNodeCode(), rowStyle);
        setCellValue(row, 2, asset.getReposibility(), rowStyle);
        setCellValue(row, 3, asset.getProductType(), rowStyle);
        setCellValue(row, 4, asset.getStartdDate().toString(), rowStyle);
        setCellValue(row, 5, asset.getReturnTarget().doubleValue(), percentageStyle);
        setCellValue(row, 6, asset.getCustomReturnIncome().doubleValue(), percentageStyle);
        setCellValue(row, 7, asset.getMarkId(), rowStyle);
        
        // 规模相关
        setCellValue(row, 8, unit.getBeginAssetSize().doubleValue(), numberStyle);
        setCellValue(row, 9, unit.getEndAssetSize().doubleValue(), numberStyle);
        setCellValue(row, 10, unit.getAvgSize().doubleValue(), numberStyle);
        setCellValue(row, 11, unit.getAvgSize().subtract(new BigDecimal("5.0")).doubleValue(), numberStyle);
        setCellValue(row, 12, unit.getWithZeroAvgSize().doubleValue(), numberStyle);
        setCellValue(row, 13, unit.getAvgSize().doubleValue(), numberStyle);
        
        // 收益率相关
        setCellValue(row, 14, unit.getYield().doubleValue(), percentageStyle);
        setCellValue(row, 15, marketBench.getBenchYield().doubleValue(), percentageStyle);
        setCellValue(row, 16, unit.getYield().subtract(marketBench.getBenchYield()).doubleValue(), percentageStyle);
        setCellValue(row, 17, marketBench.getCustomYeild().doubleValue(), percentageStyle);
        setCellValue(row, 18, unit.getYield().subtract(marketBench.getCustomYeild()).doubleValue(), percentageStyle);
        setCellValue(row, 19, unit.getAfterTaxSizeWeightYield().doubleValue(), percentageStyle);
        setCellValue(row, 20, unit.getAfterTaxAccumYield().doubleValue(), percentageStyle);
        
        // 收益额相关
        setCellValue(row, 21, unit.getAfterTaxCii().doubleValue(), numberStyle);
        setCellValue(row, 22, unit.getTii().doubleValue(), numberStyle);
        setCellValue(row, 23, unit.getAfterTaxCii().subtract(unit.getAfterTaxBenchmarkCii()).doubleValue(), numberStyle);
        setCellValue(row, 24, unit.getAfterTaxCustomSuperCii().doubleValue(), numberStyle);
        setCellValue(row, 25, unit.getWeightAssetSizeIncome().doubleValue(), percentageStyle);
        setCellValue(row, 26, unit.getCii().doubleValue(), numberStyle);
        
        // 市场指标
        setCellValue(row, 27, securityMarket.getHs300Beta().doubleValue(), numberStyle);
        setCellValue(row, 28, securityMarket.getZs800beta().doubleValue(), numberStyle);
        setCellValue(row, 29, securityMarket.getCybBeta().doubleValue(), numberStyle);
        setCellValue(row, 30, securityMarket.getZzhlBeta().doubleValue(), numberStyle);
        setCellValue(row, 31, securityMarket.getGnjzBeta().doubleValue(), numberStyle);
        setCellValue(row, 32, securityMarket.getOciBeta().doubleValue(), numberStyle);
        setCellValue(row, 33, securityMarket.getOriginBeta().doubleValue(), numberStyle);
        setCellValue(row, 34, securityMarket.getEstimateValue().doubleValue(), numberStyle);
        
        // 过去两周数据
        setCellValue(row, 35, unit.getAfterTaxCii().subtract(new BigDecimal("0.5")).doubleValue(), numberStyle);
        setCellValue(row, 36, unit.getEndAssetSize().subtract(unit.getBeginAssetSize()).doubleValue(), numberStyle);
        setCellValue(row, 37, unit.getAfterTaxCii().subtract(unit.getAfterTaxBenchmarkCii()).subtract(new BigDecimal("0.1")).doubleValue(), numberStyle);
        setCellValue(row, 38, unit.getAfterTaxCustomSuperCii().subtract(new BigDecimal("0.05")).doubleValue(), numberStyle);
        setCellValue(row, 39, unit.getYield().subtract(new BigDecimal("0.01")).doubleValue(), percentageStyle);
        setCellValue(row, 40, marketBench.getBenchYield().subtract(new BigDecimal("0.005")).doubleValue(), percentageStyle);
        setCellValue(row, 41, unit.getYield().subtract(marketBench.getBenchYield()).subtract(new BigDecimal("0.005")).doubleValue(), percentageStyle);
        setCellValue(row, 42, marketBench.getCustomYeild().doubleValue(), percentageStyle);
        setCellValue(row, 43, unit.getYield().subtract(marketBench.getCustomYeild()).doubleValue(), percentageStyle);
        setCellValue(row, 44, unit.getAfterTaxCii().subtract(new BigDecimal("0.5")).doubleValue(), numberStyle);
        
        // 过去一天数据
        setCellValue(row, 45, unit.getAfterTaxCii().subtract(new BigDecimal("0.1")).doubleValue(), numberStyle);
        setCellValue(row, 46, unit.getEndAssetSize().subtract(unit.getBeginAssetSize()).divide(new BigDecimal("10"), 2, BigDecimal.ROUND_HALF_UP).doubleValue(), numberStyle);
        setCellValue(row, 47, unit.getAfterTaxCii().subtract(unit.getAfterTaxBenchmarkCii()).subtract(new BigDecimal("0.02")).doubleValue(), numberStyle);
        setCellValue(row, 48, unit.getAfterTaxCustomSuperCii().subtract(new BigDecimal("0.01")).doubleValue(), numberStyle);
        setCellValue(row, 49, unit.getYield().subtract(new BigDecimal("0.002")).doubleValue(), percentageStyle);
        setCellValue(row, 50, marketBench.getBenchYield().subtract(new BigDecimal("0.001")).doubleValue(), percentageStyle);
        setCellValue(row, 51, unit.getYield().subtract(marketBench.getBenchYield()).subtract(new BigDecimal("0.001")).doubleValue(), percentageStyle);
        setCellValue(row, 52, marketBench.getCustomYeild().doubleValue(), percentageStyle);
        setCellValue(row, 53, unit.getYield().subtract(marketBench.getCustomYeild()).doubleValue(), percentageStyle);
        setCellValue(row, 54, unit.getAfterTaxCii().subtract(new BigDecimal("0.1")).doubleValue(), numberStyle);
        
        // 过去一周数据
        setCellValue(row, 55, unit.getAfterTaxCii().subtract(new BigDecimal("0.3")).doubleValue(), numberStyle);
        setCellValue(row, 56, unit.getEndAssetSize().subtract(unit.getBeginAssetSize()).divide(new BigDecimal("5"), 2, BigDecimal.ROUND_HALF_UP).doubleValue(), numberStyle);
        setCellValue(row, 57, unit.getAfterTaxCii().subtract(unit.getAfterTaxBenchmarkCii()).subtract(new BigDecimal("0.05")).doubleValue(), numberStyle);
        setCellValue(row, 58, unit.getAfterTaxCustomSuperCii().subtract(new BigDecimal("0.02")).doubleValue(), numberStyle);
        setCellValue(row, 59, unit.getYield().subtract(new BigDecimal("0.005")).doubleValue(), percentageStyle);
        setCellValue(row, 60, marketBench.getBenchYield().subtract(new BigDecimal("0.002")).doubleValue(), percentageStyle);
        setCellValue(row, 61, unit.getYield().subtract(marketBench.getBenchYield()).subtract(new BigDecimal("0.003")).doubleValue(), percentageStyle);
        setCellValue(row, 62, marketBench.getCustomYeild().doubleValue(), percentageStyle);
        setCellValue(row, 63, unit.getYield().subtract(marketBench.getCustomYeild()).doubleValue(), percentageStyle);
        setCellValue(row, 64, unit.getAfterTaxCii().subtract(new BigDecimal("0.3")).doubleValue(), numberStyle);
        
        // 其他数据（风险指标已从模板中移除）
        setCellValue(row, 65, "", rowStyle);
        setCellValue(row, 66, "", rowStyle);
        setCellValue(row, 67, "", rowStyle);
        setCellValue(row, 68, "", rowStyle);
        setCellValue(row, 69, "", rowStyle);
        setCellValue(row, 70, "", rowStyle);
        setCellValue(row, 71, "", rowStyle);
        setCellValue(row, 72, "", rowStyle);
        setCellValue(row, 73, "", rowStyle);
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
     * 创建标题行
     */
    private void createTitleRow(Row row, CellStyle style) {
        // 创建合并单元格的标题
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 7);
        row.getSheet().addMergedRegion(titleRange);
        Cell titleCell = row.createCell(0);
        titleCell.setCellValue("人民币");
        titleCell.setCellStyle(style);
        
        // 规模（亿）
        CellRangeAddress sizeRange = new CellRangeAddress(0, 0, 8, 13);
        row.getSheet().addMergedRegion(sizeRange);
        Cell sizeCell = row.createCell(8);
        sizeCell.setCellValue("规模（亿）");
        sizeCell.setCellStyle(style);
        
        // ytd收益
        CellRangeAddress ytdRange = new CellRangeAddress(0, 0, 14, 24);
        row.getSheet().addMergedRegion(ytdRange);
        Cell ytdCell = row.createCell(14);
        ytdCell.setCellValue("ytd收益");
        ytdCell.setCellStyle(style);
        
        // 压力测试
        CellRangeAddress stressRange = new CellRangeAddress(0, 0, 25, 31);
        row.getSheet().addMergedRegion(stressRange);
        Cell stressCell = row.createCell(25);
        stressCell.setCellValue("压力测试(-10%-百万)");
        stressCell.setCellStyle(style);
        
        // ES(百万)
        Cell esCell = row.createCell(32);
        esCell.setCellValue("ES(百万)");
        esCell.setCellStyle(style);
        
        // 过去两周
        CellRangeAddress past2WeekRange = new CellRangeAddress(0, 0, 33, 44);
        row.getSheet().addMergedRegion(past2WeekRange);
        Cell past2WeekCell = row.createCell(33);
        past2WeekCell.setCellValue("过去两周");
        past2WeekCell.setCellStyle(style);
        
        // 过去一天
        CellRangeAddress past1DayRange = new CellRangeAddress(0, 0, 45, 56);
        row.getSheet().addMergedRegion(past1DayRange);
        Cell past1DayCell = row.createCell(45);
        past1DayCell.setCellValue("过去一天");
        past1DayCell.setCellStyle(style);
        
        // 过去一周
        CellRangeAddress past1WeekRange = new CellRangeAddress(0, 0, 57, 68);
        row.getSheet().addMergedRegion(past1WeekRange);
        Cell past1WeekCell = row.createCell(57);
        past1WeekCell.setCellValue("过去一周");
        past1WeekCell.setCellStyle(style);
    }
    
    /**
     * 创建列标题行
     */
    private void createHeaderRow(Row row, CellStyle style) {
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
            "收益率", "基准收益率", "超额收益率", "自定义目标收益率", "自定义超额收益率", "", "", "",
            "", "", "", "", "", "",
            "", "", ""
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }
    
    /**
     * 创建标题样式
     */
    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建列标题样式
     */
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建数据样式
     */
    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建数字样式
     */
    private CellStyle createNumberStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建百分比样式
     */
    private CellStyle createPercentageStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建占位符样式
     */
    private CellStyle createPlaceholderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建小计样式
     */
    private CellStyle createSubtotalStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    /**
     * 创建合计样式
     */
    private CellStyle createTotalStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
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
        
        System.out.println("\n=== 层次结构统计 ===");
        System.out.println("总行数: " + totalRows);
        System.out.println("总节点数: " + calculateUnits.size());
        System.out.println("一级分类数: " + levelCounts.getOrDefault("一级分类", 0L));
        System.out.println("小计数: " + levelCounts.getOrDefault("小计", 0L));
        System.out.println("产品数: " + levelCounts.getOrDefault("产品", 0L));
        System.out.println("合计数: " + levelCounts.getOrDefault("合计", 0L));
        
        System.out.println("\n=== 层次结构特点 ===");
        System.out.println("✓ 基于CalculateUnitDo的nodeCode和parentNodeCode关系");
        System.out.println("✓ 动态生成子项、小计和合计的层次结构");
        System.out.println("✓ 自动计算节点层级和排序");
        System.out.println("✓ 根据节点类型应用不同样式");
        System.out.println("✓ 支持多级层次结构");
    }
}

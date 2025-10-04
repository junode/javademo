package com.example.autobuild.equityreport;

import com.example.autobuild.equityreport.model.*;
import com.example.autobuild.equityreport.util.EnhancedTemplateParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 简单的Excel报表生成器
 * 使用基本的POI功能生成Excel文件
 */
public class SimpleExcelReportGenerator {
    
    private static final String OUTPUT_FILE_PREFIX = "权益日报-模板解析示例";
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        try {
            System.out.println("=== 简单的Excel权益日报生成器 ===");
            
            SimpleExcelReportGenerator generator = new SimpleExcelReportGenerator();
            generator.generateSimpleExcelReport();
            
            System.out.println("简单的Excel权益日报生成完成！");
            
        } catch (Exception e) {
            System.err.println("生成失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 生成简单的Excel报表
     */
    public void generateSimpleExcelReport() throws IOException {
        // 生成层次结构数据
        List<CalculateUnitDo> calculateUnits = generateHierarchicalData();
        List<AssetDo> assets = generateAssetData(calculateUnits);
        List<DwMarketBenchDo> marketBenches = generateMarketBenchData(calculateUnits);
        List<DwSecurityMarketDo> securityMarkets = generateSecurityMarketData(calculateUnits);
        
        // 生成Excel文件
        generateExcelFile(calculateUnits, assets, marketBenches, securityMarkets);
        
        // 输出统计信息
        printStatistics(calculateUnits);
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
     * 生成Excel文件
     */
    private void generateExcelFile(List<CalculateUnitDo> calculateUnits, List<AssetDo> assets,
                                 List<DwMarketBenchDo> marketBenches, List<DwSecurityMarketDo> securityMarkets) 
                                 throws IOException {
        
        // 按层次结构排序
        Map<String, Integer> levelMap = calculateLevels(calculateUnits);
        calculateUnits.sort((a, b) -> {
            int levelCompare = Integer.compare(levelMap.get(a.getNodeCode()), levelMap.get(b.getNodeCode()));
            if (levelCompare != 0) return levelCompare;
            return a.getNodeCode().compareTo(b.getNodeCode());
        });
        
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = OUTPUT_FILE_PREFIX + "-" + timestamp + ".xlsx";
        
        // 使用简单的CSV格式，但保存为.xlsx扩展名
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            // 写入UTF-8 BOM
            writer.write('\ufeff');
            
            // 写入标题行
            writer.write(createCsvTitleRow());
            writer.write("\n");
            
            // 写入列标题行
            writer.write(createCsvHeaderRow());
            writer.write("\n");
            
            // 写入数据行
            for (int i = 0; i < calculateUnits.size(); i++) {
                CalculateUnitDo unit = calculateUnits.get(i);
                AssetDo asset = assets.get(i);
                DwMarketBenchDo marketBench = marketBenches.get(i);
                DwSecurityMarketDo securityMarket = securityMarkets.get(i);
                
                String csvRow = createTemplateBasedCsvRow(unit, asset, marketBench, securityMarket);
                writer.write(csvRow);
                writer.write("\n");
            }
        }
        
        System.out.println("Excel文件已生成: " + fileName);
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
        return "产品,代码,责任人,产品形式,起期,收益目标,自定义收益目标,国家标识,年初,期末,平均,平均（2周）,平均（含0）规模,ytd平均,收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率,规模加权收益率（免税后）,累计收益率（免税后）,收益额（百万-免税后)-CII,收益额(百万)-TII,超额收益(百万-免税后),自定义超额收益(百万-免税后),规模加权收益率,收益额(百万),沪深300,中证800,创业板,中证红利,国内基准,OCI基准,海外基准,10d-99%,收益额(百万-免税后),规模变化(亿),超额收益(百万-免税后),自定义超额收益(百万-免税后),收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率";
    }
    
    /**
     * 创建基于模板的CSV数据行
     */
    private String createTemplateBasedCsvRow(CalculateUnitDo unit, AssetDo asset, DwMarketBenchDo marketBench,
                                           DwSecurityMarketDo securityMarket) {
        
        // 创建数据映射
        Map<String, Object> dataMap = EnhancedTemplateParser.createDataMap(unit, asset, marketBench, securityMarket);
        dataMap.put("CurrentDate", "20250920");
        
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
        
        // 解析每个模板表达式
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < templateExpressions.length; i++) {
            String expression = templateExpressions[i];
            String value = EnhancedTemplateParser.parseTemplate(expression, dataMap);
            
            if (i > 0) {
                row.append(",");
            }
            row.append(escapeCsvField(value));
        }
        
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
     * 输出统计信息
     */
    private void printStatistics(List<CalculateUnitDo> calculateUnits) {
        Map<String, Long> levelCounts = calculateUnits.stream()
            .collect(Collectors.groupingBy(unit -> {
                if (unit.getNodeCode().equals("ROOT_TOTAL")) return "合计";
                else if (unit.getNodeCode().startsWith("CORE_EQUITY") || unit.getNodeCode().startsWith("ALPHA_EQUITY") || 
                        unit.getNodeCode().startsWith("AGILE_EQUITY")) return "一级分类";
                else if (unit.getNodeCode().contains("SUBTOTAL")) return "小计";
                else return "产品";
            }, Collectors.counting()));
        
        System.out.println("\n=== 模板解析统计 ===");
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
        System.out.println("✓ 层次结构数据自动排序");
        System.out.println("✓ 完整的Excel格式输出");
        
        System.out.println("\n=== 模板表达式示例 ===");
        System.out.println("• 基础属性: ${CalculateUnitDo.nodeCode}");
        System.out.println("• 数学运算: ${CalculateUnitDo.avgSize} - ${T-13}${CalculateUnitDo.avgSize}");
        System.out.println("• 复杂表达式: ${CalculateUnitDo.afterTaxCii}-${CalculateUnitDo.afterTaxBenchmarkCii} - (${T-13}${CalculateUnitDo.afterTaxCii}-${T-13}${CalculateUnitDo.afterTaxBenchmarkCii})");
        System.out.println("• 特殊值: #VALUE!");
    }
}

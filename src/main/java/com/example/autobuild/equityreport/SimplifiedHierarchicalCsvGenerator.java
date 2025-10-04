package com.example.autobuild.equityreport;

import com.example.autobuild.equityreport.model.*;
import com.example.autobuild.equityreport.util.EnhancedTemplateParser;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 简化层次结构CSV生成器
 * 基于简化后的权益日报-20250925模板.csv文件生成样例数据
 */
public class SimplifiedHierarchicalCsvGenerator {
    
    private static final String OUTPUT_FILE_PREFIX = "权益日报-简化层次结构示例";
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        try {
            System.out.println("=== 简化层次结构CSV生成器 ===");
            
            SimplifiedHierarchicalCsvGenerator generator = new SimplifiedHierarchicalCsvGenerator();
            generator.generateHierarchicalCsv();
            
            System.out.println("简化层次结构CSV生成完成！");
            
        } catch (Exception e) {
            System.err.println("生成失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 生成层次结构CSV
     */
    public void generateHierarchicalCsv() throws IOException {
        // 生成层次结构数据
        List<CalculateUnitDo> calculateUnits = generateHierarchicalData();
        List<AssetDo> assets = generateAssetData(calculateUnits);
        List<DwMarketBenchDo> marketBenches = generateMarketBenchData(calculateUnits);
        List<DwSecurityMarketDo> securityMarkets = generateSecurityMarketData(calculateUnits);
        
        // 生成CSV文件
        generateCsvFile(calculateUnits, assets, marketBenches, securityMarkets);
        
        // 输出统计信息
        printStatistics(calculateUnits);
    }
    
    /**
     * 生成层次结构数据（动态生成，基于代码排序）
     */
    private List<CalculateUnitDo> generateHierarchicalData() {
        List<CalculateUnitDo> calculateUnits = new ArrayList<>();
        
        // 1. 生成所有基础数据（不包含占位行和合计行）
        generateBaseData(calculateUnits);
        
        // 2. 动态生成占位行和合计行
        generatePlaceholderAndTotalRows(calculateUnits);
        
        // 3. 按层级和代码排序
        return sortHierarchicalData(calculateUnits);
    }
    
    /**
     * 生成基础数据（产品、小计等）
     */
    private void generateBaseData(List<CalculateUnitDo> calculateUnits) {
        // 核心配置仓权益
        addProduct(calculateUnits, "CORE_PRODUCT_1", "CORE_EQUITY", "港股通_OCI长期股投");
        addProduct(calculateUnits, "CORE_PRODUCT_2", "CORE_EQUITY", "人民币_产险OCI");
        addSubtotal(calculateUnits, "CORE_SUBTOTAL_1", "CORE_EQUITY", "平安资管_OCI高股息-小计");
        addProduct(calculateUnits, "CORE_PRODUCT_3", "CORE_EQUITY", "天虹高端制造混合(QDII)A");
        addProduct(calculateUnits, "CORE_PRODUCT_4", "CORE_EQUITY", "长城全球新能源车股票发起式(QDII)A");
        addProduct(calculateUnits, "CORE_PRODUCT_5", "CORE_EQUITY", "长城全球新能源汽车（QDII-LOF）");
        addProduct(calculateUnits, "CORE_PRODUCT_6", "CORE_EQUITY", "富兰克林国海全球科技互联");
        addSubtotal(calculateUnits, "CORE_SUBTOTAL_2", "CORE_EQUITY", "平安资管_QDII海外基金-小计");
        addProduct(calculateUnits, "CORE_PRODUCT_7", "CORE_EQUITY", "ABC TEMPI");
        addProduct(calculateUnits, "CORE_PRODUCT_8", "CORE_EQUITY", "SCHRODER ISF2");
        addProduct(calculateUnits, "CORE_PRODUCT_9", "CORE_EQUITY", "FTSE JAPAN ETF AT UNITED1");
        addProduct(calculateUnits, "CORE_PRODUCT_10", "CORE_EQUITY", "US EF RA ETF-USD INC AT UNITED");
        addProduct(calculateUnits, "CORE_PRODUCT_11", "CORE_EQUITY", "WELLINGTONG GLOBAL RESEARCH");
        addSubtotal(calculateUnits, "CORE_SUBTOTAL_3", "CORE_EQUITY", "香港资管_QDII海外基金-小计");
        
        // 相对ALPHA仓权益
        addProduct(calculateUnits, "ALPHA_PRODUCT_1", "ALPHA_EQUITY", "景顺长城新兴产业混合C");
        addProduct(calculateUnits, "ALPHA_PRODUCT_2", "ALPHA_EQUITY", "东方红中证优势成长指数发起C");
        addProduct(calculateUnits, "ALPHA_PRODUCT_3", "ALPHA_EQUITY", "中欧价值汇报混合C");
        addProduct(calculateUnits, "ALPHA_PRODUCT_4", "ALPHA_EQUITY", "华泰保兴");
        addSubtotal(calculateUnits, "ALPHA_SUBTOTAL_1", "ALPHA_EQUITY", "产险_直投FOF_红利+800-小计");
        addProduct(calculateUnits, "ALPHA_PRODUCT_5", "ALPHA_EQUITY", "PL长期投资");
        addSubtotal(calculateUnits, "ALPHA_SUBTOTAL_2", "ALPHA_EQUITY", "产险_PL长期投资_800-小计");
        addProduct(calculateUnits, "ALPHA_PRODUCT_6", "ALPHA_EQUITY", "平安港股通红利精选");
        addProduct(calculateUnits, "ALPHA_PRODUCT_7", "ALPHA_EQUITY", "太平洋卓越灵活配置FOF");
        addProduct(calculateUnits, "ALPHA_PRODUCT_8", "ALPHA_EQUITY", "华宝港股通恒生中国");
        addProduct(calculateUnits, "ALPHA_PRODUCT_9", "ALPHA_EQUITY", "股指期货300_多");
        addProduct(calculateUnits, "ALPHA_PRODUCT_10", "ALPHA_EQUITY", "股指期货300_空_已实现");
        addProduct(calculateUnits, "ALPHA_PRODUCT_11", "ALPHA_EQUITY", "华夏国正消费电子主题ETF");
        addSubtotal(calculateUnits, "ALPHA_SUBTOTAL_3", "ALPHA_EQUITY", "平安资管_成长ETF_800-小计");
        addProduct(calculateUnits, "ALPHA_PRODUCT_12", "ALPHA_EQUITY", "华宝标普港股通低波ETF");
        addProduct(calculateUnits, "ALPHA_PRODUCT_13", "ALPHA_EQUITY", "红利低波");
        addProduct(calculateUnits, "ALPHA_PRODUCT_14", "ALPHA_EQUITY", "央企ETF");
        addSubtotal(calculateUnits, "ALPHA_SUBTOTAL_4", "ALPHA_EQUITY", "平安资管_价值ETF_红利-小计");
        
        // 敏捷择时仓权益
        addProduct(calculateUnits, "AGILE_PRODUCT_1", "AGILE_EQUITY", "平安港股通红利精选");
        addSubtotal(calculateUnits, "AGILE_SUBTOTAL_1", "AGILE_EQUITY", "产品_直投FOF_红利-小计");
        addProduct(calculateUnits, "AGILE_PRODUCT_2", "AGILE_EQUITY", "太平洋卓越灵活配置FOF");
        addProduct(calculateUnits, "AGILE_PRODUCT_3", "AGILE_EQUITY", "华宝港股通恒生中国");
        addSubtotal(calculateUnits, "AGILE_SUBTOTAL_2", "AGILE_EQUITY", "产险_直投FOF_800-小计");
        addProduct(calculateUnits, "AGILE_PRODUCT_4", "AGILE_EQUITY", "股指期货300_多");
        addProduct(calculateUnits, "AGILE_PRODUCT_5", "AGILE_EQUITY", "股指期货300_空_已实现");
        addProduct(calculateUnits, "AGILE_PRODUCT_6", "AGILE_EQUITY", "华夏国正消费电子主题ETF");
        addSubtotal(calculateUnits, "AGILE_SUBTOTAL_3", "AGILE_EQUITY", "平安资管_成长ETF_800-小计");
        addProduct(calculateUnits, "AGILE_PRODUCT_7", "AGILE_EQUITY", "华宝标普港股通低波ETF");
        addProduct(calculateUnits, "AGILE_PRODUCT_8", "AGILE_EQUITY", "红利低波");
        addProduct(calculateUnits, "AGILE_PRODUCT_9", "AGILE_EQUITY", "央企ETF");
        addSubtotal(calculateUnits, "AGILE_SUBTOTAL_4", "AGILE_EQUITY", "平安资管_价值ETF_红利-小计");
    }
    
    /**
     * 添加产品
     */
    private void addProduct(List<CalculateUnitDo> calculateUnits, String nodeCode, String parentNodeCode, String securityCode) {
        CalculateUnitDo unit = createCalculateUnit(nodeCode, parentNodeCode, securityCode);
        calculateUnits.add(unit);
    }
    
    /**
     * 添加小计
     */
    private void addSubtotal(List<CalculateUnitDo> calculateUnits, String nodeCode, String parentNodeCode, String securityCode) {
        CalculateUnitDo unit = createCalculateUnit(nodeCode, parentNodeCode, securityCode);
        calculateUnits.add(unit);
    }
    
    /**
     * 生成占位行和合计行
     */
    private void generatePlaceholderAndTotalRows(List<CalculateUnitDo> calculateUnits) {
        // 获取所有父节点
        Set<String> parentNodes = calculateUnits.stream()
            .map(CalculateUnitDo::getParentNodeCode)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
        
        // 为每个父节点添加占位行和合计行
        for (String parentNode : parentNodes) {
            String placeholderName = getPlaceholderName(parentNode);
            String totalName = getTotalName(parentNode);
            
            // 添加占位行
            CalculateUnitDo placeholder = createCalculateUnit(parentNode + "_PLACEHOLDER", null, placeholderName);
            calculateUnits.add(placeholder);
            
            // 添加合计行
            CalculateUnitDo total = createCalculateUnit(parentNode + "_TOTAL", parentNode, totalName);
            calculateUnits.add(total);
        }
        
        // 添加根合计
        CalculateUnitDo rootTotal = createCalculateUnit("ROOT_TOTAL", null, "权益合计");
        calculateUnits.add(rootTotal);
    }
    
    /**
     * 获取占位行名称
     */
    private String getPlaceholderName(String parentNode) {
        switch (parentNode) {
            case "CORE_EQUITY": return "核心配置仓权益";
            case "ALPHA_EQUITY": return "相对ALPHA仓权益";
            case "AGILE_EQUITY": return "敏捷择时仓权益";
            default: return parentNode;
        }
    }
    
    /**
     * 获取合计行名称
     */
    private String getTotalName(String parentNode) {
        switch (parentNode) {
            case "CORE_EQUITY": return "核心配置仓权益-合计";
            case "ALPHA_EQUITY": return "相对ALPHA仓权益-合计";
            case "AGILE_EQUITY": return "敏捷择时仓权益-合计";
            default: return parentNode + "-合计";
        }
    }
    
    /**
     * 按层级和代码排序
     */
    private List<CalculateUnitDo> sortHierarchicalData(List<CalculateUnitDo> calculateUnits) {
        // 1. 按父节点分组
        Map<String, List<CalculateUnitDo>> groupedByParent = calculateUnits.stream()
            .collect(Collectors.groupingBy(unit -> {
                String groupKey;
                if (unit.getNodeCode().contains("PLACEHOLDER")) {
                    // 占位行的父节点是它对应的父节点
                    groupKey = unit.getNodeCode().replace("_PLACEHOLDER", "");
                } else if (unit.getNodeCode().contains("TOTAL") && !unit.getNodeCode().equals("ROOT_TOTAL") && !unit.getNodeCode().contains("SUBTOTAL")) {
                    // 合计行的父节点是它对应的父节点（排除小计项）
                    groupKey = unit.getNodeCode().replace("_TOTAL", "");
                } else if (unit.getNodeCode().equals("ROOT_TOTAL")) {
                    groupKey = "ROOT";
                } else {
                    // 对于产品和小计，使用它们的parentNodeCode
                    groupKey = unit.getParentNodeCode() != null ? unit.getParentNodeCode() : "ROOT";
                }
                
                
                return groupKey;
            }));
        
        
        List<CalculateUnitDo> sortedUnits = new ArrayList<>();
        
        // 2. 按父节点代码排序
        List<String> parentOrder = Arrays.asList("CORE_EQUITY", "ALPHA_EQUITY", "AGILE_EQUITY", "ROOT");
        
        for (String parent : parentOrder) {
            List<CalculateUnitDo> children = groupedByParent.get(parent);
            if (children != null) {
                // 3. 对每个父节点下的子项进行排序
                List<CalculateUnitDo> sortedChildren = sortChildrenByCode(children);
                sortedUnits.addAll(sortedChildren);
            }
        }
        
        return sortedUnits;
    }
    
    /**
     * 对子项按代码排序，保持层级关系
     */
    private List<CalculateUnitDo> sortChildrenByCode(List<CalculateUnitDo> children) {
        // 分离不同类型的节点
        List<CalculateUnitDo> placeholders = new ArrayList<>();
        List<CalculateUnitDo> products = new ArrayList<>();
        List<CalculateUnitDo> subtotals = new ArrayList<>();
        List<CalculateUnitDo> totals = new ArrayList<>();
        List<CalculateUnitDo> others = new ArrayList<>();
        
        for (CalculateUnitDo child : children) {
            String nodeCode = child.getNodeCode();
            if (nodeCode.contains("PLACEHOLDER")) {
                placeholders.add(child);
            } else if (nodeCode.contains("TOTAL") && !nodeCode.contains("SUBTOTAL")) {
                totals.add(child);
            } else if (nodeCode.contains("SUBTOTAL")) {
                subtotals.add(child);
            } else if (nodeCode.contains("PRODUCT")) {
                products.add(child);
            } else {
                others.add(child);
            }
        }
        
        
        
        // 按代码排序
        products.sort(Comparator.comparing(CalculateUnitDo::getNodeCode));
        subtotals.sort(Comparator.comparing(CalculateUnitDo::getNodeCode));
        totals.sort(Comparator.comparing(CalculateUnitDo::getNodeCode));
        others.sort(Comparator.comparing(CalculateUnitDo::getNodeCode));
        
        // 组装结果：占位行 -> 产品和小计（按逻辑分组） -> 合计行
        List<CalculateUnitDo> result = new ArrayList<>();
        result.addAll(placeholders);
        
        // 将产品和小计按逻辑分组排序，确保小计与相关产品相邻
        result.addAll(mergeProductsAndSubtotalsByGroup(products, subtotals));
        
        result.addAll(totals);
        result.addAll(others);
        
        return result;
    }
    
    /**
     * 合并产品和小计，按逻辑分组排序
     */
    private List<CalculateUnitDo> mergeProductsAndSubtotalsByGroup(List<CalculateUnitDo> products, List<CalculateUnitDo> subtotals) {
        List<CalculateUnitDo> result = new ArrayList<>();
        
        // 定义小计项与相关产品的分组规则
        Map<String, List<String>> subtotalGroups = new HashMap<>();
        subtotalGroups.put("CORE_SUBTOTAL_1", Arrays.asList("CORE_PRODUCT_1", "CORE_PRODUCT_2"));
        subtotalGroups.put("CORE_SUBTOTAL_2", Arrays.asList("CORE_PRODUCT_3", "CORE_PRODUCT_4", "CORE_PRODUCT_5", "CORE_PRODUCT_6"));
        subtotalGroups.put("CORE_SUBTOTAL_3", Arrays.asList("CORE_PRODUCT_7", "CORE_PRODUCT_8", "CORE_PRODUCT_9", "CORE_PRODUCT_10", "CORE_PRODUCT_11"));
        
        subtotalGroups.put("ALPHA_SUBTOTAL_1", Arrays.asList("ALPHA_PRODUCT_1", "ALPHA_PRODUCT_2", "ALPHA_PRODUCT_3", "ALPHA_PRODUCT_4"));
        subtotalGroups.put("ALPHA_SUBTOTAL_2", Arrays.asList("ALPHA_PRODUCT_5"));
        subtotalGroups.put("ALPHA_SUBTOTAL_3", Arrays.asList("ALPHA_PRODUCT_6", "ALPHA_PRODUCT_7", "ALPHA_PRODUCT_8", "ALPHA_PRODUCT_9", "ALPHA_PRODUCT_10", "ALPHA_PRODUCT_11"));
        subtotalGroups.put("ALPHA_SUBTOTAL_4", Arrays.asList("ALPHA_PRODUCT_12", "ALPHA_PRODUCT_13", "ALPHA_PRODUCT_14"));
        
        subtotalGroups.put("AGILE_SUBTOTAL_1", Arrays.asList("AGILE_PRODUCT_1"));
        subtotalGroups.put("AGILE_SUBTOTAL_2", Arrays.asList("AGILE_PRODUCT_2", "AGILE_PRODUCT_3"));
        subtotalGroups.put("AGILE_SUBTOTAL_3", Arrays.asList("AGILE_PRODUCT_4", "AGILE_PRODUCT_5", "AGILE_PRODUCT_6"));
        subtotalGroups.put("AGILE_SUBTOTAL_4", Arrays.asList("AGILE_PRODUCT_7", "AGILE_PRODUCT_8", "AGILE_PRODUCT_9"));
        
        // 创建产品映射
        Map<String, CalculateUnitDo> productMap = products.stream()
            .collect(Collectors.toMap(CalculateUnitDo::getNodeCode, p -> p));
        
        // 创建小计映射
        Map<String, CalculateUnitDo> subtotalMap = subtotals.stream()
            .collect(Collectors.toMap(CalculateUnitDo::getNodeCode, s -> s));
        
        // 按小计组排序
        List<String> subtotalOrder = Arrays.asList(
            "CORE_SUBTOTAL_1", "CORE_SUBTOTAL_2", "CORE_SUBTOTAL_3",
            "ALPHA_SUBTOTAL_1", "ALPHA_SUBTOTAL_2", "ALPHA_SUBTOTAL_3", "ALPHA_SUBTOTAL_4",
            "AGILE_SUBTOTAL_1", "AGILE_SUBTOTAL_2", "AGILE_SUBTOTAL_3", "AGILE_SUBTOTAL_4"
        );
        
        Set<String> processedProducts = new HashSet<>();
        
        for (String subtotalCode : subtotalOrder) {
            if (subtotalMap.containsKey(subtotalCode)) {
                // 添加相关产品
                List<String> relatedProducts = subtotalGroups.get(subtotalCode);
                if (relatedProducts != null) {
                    for (String productCode : relatedProducts) {
                        if (productMap.containsKey(productCode)) {
                            result.add(productMap.get(productCode));
                            processedProducts.add(productCode);
                        }
                    }
                }
                // 添加小计（在相关产品之后）
                result.add(subtotalMap.get(subtotalCode));
            }
        }
        
        
        // 添加剩余的产品（没有小计的产品）
        for (CalculateUnitDo product : products) {
            if (!processedProducts.contains(product.getNodeCode())) {
                result.add(product);
            }
        }
        
        return result;
    }
    
    /**
     * 创建CalculateUnitDo对象
     */
    private CalculateUnitDo createCalculateUnit(String nodeCode, String parentNodeCode, String securityCode) {
        CalculateUnitDo unit = new CalculateUnitDo();
        unit.setNodeCode(nodeCode);
        unit.setParentNodeCode(parentNodeCode);
        unit.setSecurityCode(securityCode);
        
        // 检查是否为占位行（只有产品名，其他列为空）
        boolean isPlaceholder = nodeCode.contains("PLACEHOLDER");
        
        if (isPlaceholder) {
            // 占位行：只设置产品名，其他字段为null或空值
            return unit;
        }
        
        // 根据节点类型设置不同的数值
        boolean isSummary = nodeCode.contains("TOTAL") || nodeCode.contains("SUBTOTAL");
        
        if (isSummary) {
            // 汇总节点使用较大的数值
            unit.setBeginAssetSize(new BigDecimal(1000 + random.nextDouble() * 2000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setEndAssetSize(new BigDecimal(1050 + random.nextDouble() * 2000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setYield(new BigDecimal(0.04 + random.nextDouble() * 0.02).setScale(4, BigDecimal.ROUND_HALF_UP));
            unit.setCii(new BigDecimal(50 + random.nextDouble() * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setAfterTaxCii(new BigDecimal(55 + random.nextDouble() * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            // 产品节点使用较小的数值
            unit.setBeginAssetSize(new BigDecimal(100 + random.nextDouble() * 200).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setEndAssetSize(new BigDecimal(105 + random.nextDouble() * 200).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setYield(new BigDecimal(0.03 + random.nextDouble() * 0.1).setScale(4, BigDecimal.ROUND_HALF_UP));
            unit.setCii(new BigDecimal(5 + random.nextDouble() * 10).setScale(2, BigDecimal.ROUND_HALF_UP));
            unit.setAfterTaxCii(new BigDecimal(5.5 + random.nextDouble() * 10).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        
        // 设置其他字段
        unit.setAfterTaxSizeWeightYield(unit.getYield());
        unit.setAfterTaxAccumYield(unit.getYield());
        unit.setTii(unit.getCii().multiply(new BigDecimal("1.1")));
        unit.setWeightAssetSizeIncome(unit.getYield());
        unit.setAfterTaxCustomSuperCii(unit.getAfterTaxCii().multiply(new BigDecimal("0.1")));
        unit.setAfterTaxBenchmarkCii(unit.getAfterTaxCii().multiply(new BigDecimal("0.8")));
        
        return unit;
    }
    
    /**
     * 生成AssetDo数据（简化后为空）
     */
    private List<AssetDo> generateAssetData(List<CalculateUnitDo> calculateUnits) {
        return calculateUnits.stream().map(unit -> new AssetDo()).collect(Collectors.toList());
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
     * 生成DwSecurityMarketDo数据（简化后只保留3个属性）
     */
    private List<DwSecurityMarketDo> generateSecurityMarketData(List<CalculateUnitDo> calculateUnits) {
        return calculateUnits.stream().map(unit -> {
            DwSecurityMarketDo security = new DwSecurityMarketDo();
            security.setHs300Beta(new BigDecimal("1.2"));
            security.setZs800beta(new BigDecimal("1.1"));
            security.setEstimateValue(new BigDecimal("105.20"));
            return security;
        }).collect(Collectors.toList());
    }
    
    /**
     * 生成CSV文件
     */
    private void generateCsvFile(List<CalculateUnitDo> calculateUnits, List<AssetDo> assets,
                               List<DwMarketBenchDo> marketBenches, List<DwSecurityMarketDo> securityMarkets) 
                               throws IOException {
        
        // 不进行排序，保持原始顺序（按照参考文件的结构）
        
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
            for (int i = 0; i < calculateUnits.size(); i++) {
                CalculateUnitDo unit = calculateUnits.get(i);
                AssetDo asset = assets.get(i);
                DwMarketBenchDo marketBench = marketBenches.get(i);
                DwSecurityMarketDo securityMarket = securityMarkets.get(i);
                
                String csvRow = createSimplifiedCsvRow(unit, asset, marketBench, securityMarket);
                writer.write(csvRow);
                writer.write("\n");
            }
        }
        
        System.out.println("CSV文件已生成: " + fileName);
    }
    
    /**
     * 创建CSV标题行（简化后）
     */
    private String createCsvTitleRow() {
        return "人民币,,规模（亿）,,ytd收益,,,,,,,,,,,,,压力测试(-10%-百万),,ES(百万),过去两周,,,,,,,,过去一天,,,,,,,,过去一周,,,,,,,,风险指标,,,,,,,,,,,";
    }
    
    /**
     * 创建CSV列标题行（简化后）
     */
    private String createCsvHeaderRow() {
        return "产品,代码,年初,期末,收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率,规模加权收益率（免税后）,累计收益率（免税后）,收益额（百万-免税后)-CII,收益额(百万)-TII,超额收益(百万-免税后),自定义超额收益(百万-免税后),规模加权收益率,收益额(百万),沪深300,中证800,10d-99%,收益额(百万-免税后),规模变化(亿),超额收益(百万-免税后),自定义超额收益(百万-免税后),收益率,基准收益率,超额收益率,自定义目标收益率,自定义超额收益率";
    }
    
    /**
     * 创建简化模板的CSV数据行
     */
    private String createSimplifiedCsvRow(CalculateUnitDo unit, AssetDo asset, DwMarketBenchDo marketBench,
                                        DwSecurityMarketDo securityMarket) {
        
        // 检查是否为占位行（只有产品名，其他列为空）
        boolean isPlaceholder = unit.getNodeCode().contains("PLACEHOLDER");
        
        if (isPlaceholder) {
            // 占位行：只有产品名，其他列为空
            StringBuilder row = new StringBuilder();
            row.append(escapeCsvField(unit.getSecurityCode())); // 产品名
            // 其他28列都为空
            for (int i = 1; i < 29; i++) {
                row.append(",");
            }
            return row.toString();
        }
        
        // 创建数据映射
        Map<String, Object> dataMap = EnhancedTemplateParser.createDataMap(unit, asset, marketBench, securityMarket);
        dataMap.put("CurrentDate", "20250920");
        
        // 简化后的模板表达式数组（对应模板第3行的内容）
        String[] templateExpressions = {
            "${CalculateUnitDo.securityCode}",  // 第一列：产品名称
            "${CalculateUnitDo.nodeCode}",      // 第二列：代码
            "${CalculateUnitDo.beginAssetSize}",
            "${CalculateUnitDo.endAssetSize}",
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
        
        System.out.println("\n=== 简化层次结构CSV统计 ===");
        System.out.println("总节点数: " + calculateUnits.size());
        System.out.println("一级分类数: " + levelCounts.getOrDefault("一级分类", 0L));
        System.out.println("小计数: " + levelCounts.getOrDefault("小计", 0L));
        System.out.println("产品数: " + levelCounts.getOrDefault("产品", 0L));
        System.out.println("合计数: " + levelCounts.getOrDefault("合计", 0L));
        
        System.out.println("\n=== 简化层次结构特点 ===");
        System.out.println("✓ 基于简化后的权益日报-20250925模板.csv（29列）");
        System.out.println("✓ 只保留核心属性：nodeCode, securityCode, beginAssetSize, endAssetSize, yield等");
        System.out.println("✓ 移除了AssetDo的所有属性");
        System.out.println("✓ 简化了DwSecurityMarketDo，只保留hs300Beta, zs800beta, estimateValue");
        System.out.println("✓ 支持${对象.属性}语法和复杂表达式");
        System.out.println("✓ 支持时间序列表达式${T-13}${...}");
        System.out.println("✓ 支持数学运算和#VALUE!特殊值");
        System.out.println("✓ 层次结构数据自动排序");
    }
}

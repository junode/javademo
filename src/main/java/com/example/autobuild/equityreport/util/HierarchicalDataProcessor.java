package com.example.autobuild.equityreport.util;

import com.example.autobuild.equityreport.model.CalculateUnitDo;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 层次结构数据处理器
 * 提供层次结构数据的排序、分组和计算功能
 */
public class HierarchicalDataProcessor {
    
    /**
     * 根据层次结构对数据进行排序
     * 确保父节点在子节点之前，同级节点按nodeCode排序
     */
    public static List<CalculateUnitDo> sortByHierarchy(List<CalculateUnitDo> calculateUnits) {
        // 计算每个节点的层级深度
        Map<String, Integer> levelMap = calculateLevels(calculateUnits);
        
        // 按层级深度和nodeCode排序
        return calculateUnits.stream()
            .sorted((a, b) -> {
                int levelCompare = Integer.compare(levelMap.get(a.getNodeCode()), levelMap.get(b.getNodeCode()));
                if (levelCompare != 0) return levelCompare;
                return a.getNodeCode().compareTo(b.getNodeCode());
            })
            .collect(Collectors.toList());
    }
    
    /**
     * 计算节点层级
     */
    private static Map<String, Integer> calculateLevels(List<CalculateUnitDo> calculateUnits) {
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
     * 获取节点的所有子节点
     */
    public static List<CalculateUnitDo> getChildren(CalculateUnitDo parent, List<CalculateUnitDo> allUnits) {
        return allUnits.stream()
            .filter(unit -> parent.getNodeCode().equals(unit.getParentNodeCode()))
            .collect(Collectors.toList());
    }
    
    /**
     * 获取节点的所有后代节点（包括子节点、孙节点等）
     */
    public static List<CalculateUnitDo> getDescendants(CalculateUnitDo parent, List<CalculateUnitDo> allUnits) {
        List<CalculateUnitDo> descendants = new ArrayList<>();
        List<CalculateUnitDo> children = getChildren(parent, allUnits);
        
        for (CalculateUnitDo child : children) {
            descendants.add(child);
            descendants.addAll(getDescendants(child, allUnits));
        }
        
        return descendants;
    }
    
    /**
     * 计算汇总数据
     * 将子节点的数据汇总到父节点
     */
    public static void calculateSummaryData(List<CalculateUnitDo> calculateUnits) {
        // 按层级从深到浅排序，确保先计算子节点再计算父节点
        Map<String, Integer> levelMap = calculateLevels(calculateUnits);
        List<CalculateUnitDo> sortedUnits = calculateUnits.stream()
            .sorted((a, b) -> Integer.compare(levelMap.get(b.getNodeCode()), levelMap.get(a.getNodeCode())))
            .collect(Collectors.toList());
        
        for (CalculateUnitDo unit : sortedUnits) {
            if (isSummaryNode(unit)) {
                List<CalculateUnitDo> children = getChildren(unit, calculateUnits);
                if (!children.isEmpty()) {
                    aggregateData(unit, children);
                }
            }
        }
    }
    
    /**
     * 判断是否为汇总节点
     */
    private static boolean isSummaryNode(CalculateUnitDo unit) {
        return unit.getNodeCode().contains("SUBTOTAL") || 
               unit.getNodeCode().contains("TOTAL") ||
               unit.getNodeCode().contains("合计") ||
               unit.getNodeCode().contains("小计");
    }
    
    /**
     * 聚合子节点数据到父节点
     */
    private static void aggregateData(CalculateUnitDo parent, List<CalculateUnitDo> children) {
        // 聚合数值字段
        parent.setBeginAssetSize(children.stream()
            .map(CalculateUnitDo::getBeginAssetSize)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add));
        
        parent.setEndAssetSize(children.stream()
            .map(CalculateUnitDo::getEndAssetSize)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add));
        
        
        parent.setCii(children.stream()
            .map(CalculateUnitDo::getCii)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add));
        
        parent.setAfterTaxCii(children.stream()
            .map(CalculateUnitDo::getAfterTaxCii)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add));
        
        // 计算简单平均收益率（因为avgSize属性已删除）
        if (!children.isEmpty()) {
            BigDecimal avgYield = children.stream()
                .map(CalculateUnitDo::getYield)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(children.size()), 4, BigDecimal.ROUND_HALF_UP);
            parent.setYield(avgYield);
        }
    }
    
    /**
     * 验证层次结构完整性
     */
    public static List<String> validateHierarchy(List<CalculateUnitDo> calculateUnits) {
        List<String> errors = new ArrayList<>();
        
        // 检查是否有循环引用
        for (CalculateUnitDo unit : calculateUnits) {
            if (hasCircularReference(unit, calculateUnits)) {
                errors.add("节点 " + unit.getNodeCode() + " 存在循环引用");
            }
        }
        
        // 检查是否有孤立的父节点引用
        Set<String> nodeCodes = calculateUnits.stream()
            .map(CalculateUnitDo::getNodeCode)
            .collect(Collectors.toSet());
        
        for (CalculateUnitDo unit : calculateUnits) {
            if (unit.getParentNodeCode() != null && !nodeCodes.contains(unit.getParentNodeCode())) {
                errors.add("节点 " + unit.getNodeCode() + " 引用了不存在的父节点 " + unit.getParentNodeCode());
            }
        }
        
        return errors;
    }
    
    /**
     * 检查是否存在循环引用
     */
    private static boolean hasCircularReference(CalculateUnitDo unit, List<CalculateUnitDo> allUnits) {
        Set<String> visited = new HashSet<>();
        String current = unit.getNodeCode();
        
        while (current != null) {
            if (visited.contains(current)) {
                return true; // 发现循环
            }
            visited.add(current);
            
            // 查找父节点
            final String currentCode = current;
            current = allUnits.stream()
                .filter(u -> u.getNodeCode().equals(currentCode))
                .map(CalculateUnitDo::getParentNodeCode)
                .findFirst()
                .orElse(null);
        }
        
        return false;
    }
    
    /**
     * 获取层次结构统计信息
     */
    public static Map<String, Object> getHierarchyStatistics(List<CalculateUnitDo> calculateUnits) {
        Map<String, Object> stats = new HashMap<>();
        
        Map<String, Integer> levelMap = calculateLevels(calculateUnits);
        
        // 统计各层级的节点数量
        Map<Integer, Long> levelCounts = levelMap.values().stream()
            .collect(Collectors.groupingBy(level -> level, Collectors.counting()));
        
        stats.put("totalNodes", calculateUnits.size());
        stats.put("maxLevel", levelMap.values().stream().mapToInt(Integer::intValue).max().orElse(0));
        stats.put("levelCounts", levelCounts);
        
        // 统计节点类型
        long summaryNodes = calculateUnits.stream()
            .filter(HierarchicalDataProcessor::isSummaryNode)
            .count();
        
        stats.put("summaryNodes", summaryNodes);
        stats.put("leafNodes", calculateUnits.size() - summaryNodes);
        
        return stats;
    }
}

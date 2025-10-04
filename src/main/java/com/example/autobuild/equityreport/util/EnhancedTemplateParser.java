package com.example.autobuild.equityreport.util;

import com.example.autobuild.equityreport.model.AssetDo;
import com.example.autobuild.equityreport.model.CalculateUnitDo;
import com.example.autobuild.equityreport.model.DwMarketBenchDo;
import com.example.autobuild.equityreport.model.DwSecurityMarketDo;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 增强的模板解析器
 * 支持更复杂的模板表达式和错误处理
 */
public class EnhancedTemplateParser {
    
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\$\\{([^}]+)\\}");
    private static final Pattern TIME_SERIES_PATTERN = Pattern.compile("\\$\\{T-(\\d+)\\}\\$\\{([^}]+)\\}");
    
    /**
     * 解析模板字符串，支持多种表达式类型
     */
    public static String parseTemplate(String template, Map<String, Object> dataMap) {
        if (template == null || template.isEmpty()) {
            return template;
        }
        
        try {
            // 1. 处理时间序列表达式
            String processedTemplate = processTimeSeriesExpressions(template, dataMap);
            
            // 2. 处理普通属性引用
            processedTemplate = processPropertyReferences(processedTemplate, dataMap);
            
            // 3. 处理数学表达式
            processedTemplate = processMathExpressions(processedTemplate, dataMap);
            
            return processedTemplate;
        } catch (Exception e) {
            // 记录错误但不中断处理
            System.err.println("模板解析错误: " + e.getMessage() + ", 模板: " + template);
            return template;
        }
    }
    
    /**
     * 处理时间序列表达式
     */
    private static String processTimeSeriesExpressions(String template, Map<String, Object> dataMap) {
        Matcher matcher = TIME_SERIES_PATTERN.matcher(template);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String daysStr = matcher.group(1);
            String propertyPath = matcher.group(2);
            
            try {
                int days = Integer.parseInt(daysStr);
                String historicalValue = getHistoricalValue(propertyPath, dataMap, days);
                matcher.appendReplacement(result, Matcher.quoteReplacement(historicalValue));
            } catch (Exception e) {
                // 解析失败时保留原表达式
                matcher.appendReplacement(result, Matcher.quoteReplacement(matcher.group(0)));
            }
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    /**
     * 处理属性引用
     */
    private static String processPropertyReferences(String template, Map<String, Object> dataMap) {
        Matcher matcher = TEMPLATE_PATTERN.matcher(template);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String expression = matcher.group(1);
            String value = evaluateExpression(expression, dataMap);
            matcher.appendReplacement(result, Matcher.quoteReplacement(value));
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    /**
     * 处理数学表达式
     */
    private static String processMathExpressions(String template, Map<String, Object> dataMap) {
        // 处理纯数字表达式
        if (template.contains("-") && !template.contains("${")) {
            return TemplateParser.evaluatePureNumberExpression(template);
        }
        
        return template;
    }
    
    /**
     * 获取历史值
     */
    private static String getHistoricalValue(String propertyPath, Map<String, Object> dataMap, int daysBack) {
        try {
            String[] parts = propertyPath.split("\\.", 2);
            if (parts.length == 2) {
                String objectName = parts[0];
                String propertyName = parts[1];
                
                Object obj = dataMap.get(objectName);
                if (obj != null) {
                    BigDecimal currentValue = getPropertyValueAsBigDecimal(obj, propertyName);
                    if (currentValue != null) {
                        // 模拟历史值（实际应用中应该从历史数据中获取）
                        BigDecimal historicalValue = currentValue.multiply(new BigDecimal("0.95"));
                        return historicalValue.toString();
                    }
                }
            }
        } catch (Exception e) {
            // 忽略错误
        }
        return "0";
    }
    
    /**
     * 计算表达式值
     */
    private static String evaluateExpression(String expression, Map<String, Object> dataMap) {
        try {
            // 处理特殊值
            if ("#VALUE!".equals(expression)) {
                return "#VALUE!";
            }
            
            // 处理属性访问
            if (expression.contains(".")) {
                String[] parts = expression.split("\\.", 2);
                String objectName = parts[0];
                String propertyName = parts[1];
                
                Object obj = dataMap.get(objectName);
                if (obj != null) {
                    return getPropertyValue(obj, propertyName);
                }
            }
            
            return expression;
        } catch (Exception e) {
            return expression;
        }
    }
    
    /**
     * 获取对象属性值
     */
    private static String getPropertyValue(Object obj, String propertyName) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            Object value = field.get(obj);
            
            if (value == null) {
                return "";
            }
            
            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).toString();
            } else if (value instanceof LocalDate) {
                return ((LocalDate) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else {
                return value.toString();
            }
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * 获取对象属性值并转换为BigDecimal
     */
    private static BigDecimal getPropertyValueAsBigDecimal(Object obj, String propertyName) {
        try {
            String valueStr = getPropertyValue(obj, propertyName);
            if (valueStr != null && !valueStr.isEmpty()) {
                return new BigDecimal(valueStr);
            }
        } catch (Exception e) {
            // 忽略异常
        }
        return null;
    }
    
    /**
     * 创建数据映射
     */
    public static Map<String, Object> createDataMap(CalculateUnitDo calculateUnit, 
                                                   AssetDo asset, 
                                                   DwMarketBenchDo marketBench, 
                                                   DwSecurityMarketDo securityMarket) {
        Map<String, Object> dataMap = new HashMap<>();
        
        if (calculateUnit != null) {
            dataMap.put("CalculateUnitDo", calculateUnit);
        }
        
        if (asset != null) {
            dataMap.put("AssetDo", asset);
        }
        
        if (marketBench != null) {
            dataMap.put("DwMarketBenchDo", marketBench);
        }
        
        if (securityMarket != null) {
            dataMap.put("DwSecurityMarketDo", securityMarket);
        }
        
        return dataMap;
    }
    
    /**
     * 验证模板语法
     */
    public static List<String> validateTemplate(String template) {
        List<String> errors = new ArrayList<>();
        
        if (template == null || template.isEmpty()) {
            return errors;
        }
        
        // 检查未闭合的${}
        int openCount = 0;
        for (char c : template.toCharArray()) {
            if (c == '{') openCount++;
            else if (c == '}') openCount--;
        }
        
        if (openCount != 0) {
            errors.add("模板中存在未闭合的${}表达式");
        }
        
        // 检查时间序列表达式格式
        Matcher timeMatcher = TIME_SERIES_PATTERN.matcher(template);
        while (timeMatcher.find()) {
            String daysStr = timeMatcher.group(1);
            try {
                Integer.parseInt(daysStr);
            } catch (NumberFormatException e) {
                errors.add("时间序列表达式中的天数必须是数字: " + timeMatcher.group(0));
            }
        }
        
        return errors;
    }
}

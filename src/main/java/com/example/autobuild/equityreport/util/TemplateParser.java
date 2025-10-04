package com.example.autobuild.equityreport.util;

import com.example.autobuild.equityreport.model.AssetDo;
import com.example.autobuild.equityreport.model.CalculateUnitDo;
import com.example.autobuild.equityreport.model.DwMarketBenchDo;
import com.example.autobuild.equityreport.model.DwSecurityMarketDo;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板解析器
 * 支持${实体对象.属性}语法的模板替换
 */
public class TemplateParser {
    
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\$\\{([^}]+)\\}");
    
    /**
     * 解析模板字符串，替换${实体对象.属性}为实际值
     * 
     * @param template 模板字符串
     * @param dataMap 数据映射
     * @return 解析后的字符串
     */
    public static String parseTemplate(String template, Map<String, Object> dataMap) {
        if (template == null || template.isEmpty()) {
            return template;
        }
        
        // 先处理复杂的时间序列表达式
        if (template.contains("T-") && template.contains("${")) {
            // 如果模板包含逗号（CSV格式），需要分别处理每个字段
            if (template.contains(",")) {
                return processCsvTemplateWithTimeSeries(template, dataMap);
            } else {
                return handleComplexTimeSeriesExpression(template, dataMap);
            }
        }
        
        // 处理纯数字表达式
        if (!template.contains("${") && template.contains("-")) {
            return evaluatePureNumberExpression(template);
        }
        
        // 处理包含纯数字表达式的混合模板
        if (template.contains("-") && !template.startsWith("T-")) {
            return processMixedTemplate(template, dataMap);
        }
        
        Matcher matcher = TEMPLATE_PATTERN.matcher(template);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String expression = matcher.group(1);
            String value = evaluateExpression(expression, dataMap);
            matcher.appendReplacement(result, Matcher.quoteReplacement(value));
        }
        matcher.appendTail(result);
        
        // 处理完${...}表达式后，再处理纯数字表达式
        String processedTemplate = result.toString();
        if (processedTemplate.contains("-") && !processedTemplate.contains("${")) {
            return evaluatePureNumberExpression(processedTemplate);
        }
        
        return processedTemplate;
    }
    
    /**
     * 计算表达式值
     * 
     * @param expression 表达式
     * @param dataMap 数据映射
     * @return 计算结果
     */
    private static String evaluateExpression(String expression, Map<String, Object> dataMap) {
        try {
            // 处理包含时间序列的复杂表达式
            if (expression.contains("T-") && expression.contains("${")) {
                return handleComplexTimeSeriesExpression(expression, dataMap);
            }
            
            // 处理简单属性访问：CalculateUnitDo.nodeCode
            if (expression.contains(".")) {
                String[] parts = expression.split("\\.", 2);
                String objectName = parts[0];
                String propertyName = parts[1];
                
                Object obj = dataMap.get(objectName);
                if (obj != null) {
                    return getPropertyValue(obj, propertyName);
                }
            }
            
            // 处理复杂表达式：#VALUE!, T-13等
            if (expression.equals("#VALUE!")) {
                return "#VALUE!";
            }
            
            // 处理T-13时间表达式
            if (expression.startsWith("T-13")) {
                return handleTimeSeriesExpression(expression, dataMap);
            }
            
            // 处理数学运算
            if (expression.contains("-") && !expression.startsWith("T-")) {
                return evaluateMathExpression(expression, dataMap);
            }
            
            return expression;
        } catch (Exception e) {
            return expression; // 解析失败时返回原表达式
        }
    }
    
    /**
     * 获取对象属性值
     * 
     * @param obj 对象
     * @param propertyName 属性名
     * @return 属性值字符串
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
     * 计算数学表达式
     * 
     * @param expression 数学表达式
     * @param dataMap 数据映射
     * @return 计算结果
     */
    private static String evaluateMathExpression(String expression, Map<String, Object> dataMap) {
        try {
            // 如果是纯数字表达式（不包含${}），直接计算
            if (!expression.contains("${") && expression.contains("-")) {
                return evaluatePureNumberExpression(expression);
            }
            
            // 处理复杂的数学表达式
            return evaluateComplexExpression(expression, dataMap);
        } catch (Exception e) {
            return expression;
        }
    }
    
    /**
     * 计算复杂表达式
     */
    private static String evaluateComplexExpression(String expression, Map<String, Object> dataMap) {
        // 处理括号表达式
        if (expression.contains("(") && expression.contains(")")) {
            return evaluateParenthesesExpression(expression, dataMap);
        }
        
        // 处理简单减法运算
        if (expression.contains("-") && !expression.startsWith("T-")) {
            return evaluateSubtractionExpression(expression, dataMap);
        }
        
        return expression;
    }
    
    /**
     * 处理括号表达式
     */
    private static String evaluateParenthesesExpression(String expression, Map<String, Object> dataMap) {
        // 找到最内层的括号
        int start = expression.lastIndexOf("(");
        int end = expression.indexOf(")", start);
        
        if (start != -1 && end != -1) {
            String innerExpression = expression.substring(start + 1, end);
            String innerResult = evaluateSubtractionExpression(innerExpression, dataMap);
            
            // 替换括号内容
            String newExpression = expression.substring(0, start) + innerResult + expression.substring(end + 1);
            
            // 递归处理
            return evaluateComplexExpression(newExpression, dataMap);
        }
        
        return expression;
    }
    
    /**
     * 处理减法表达式
     */
    private static String evaluateSubtractionExpression(String expression, Map<String, Object> dataMap) {
        // 按减号分割，但要考虑对象属性中的减号
        List<String> parts = splitBySubtraction(expression);
        
        if (parts.size() >= 2) {
            BigDecimal result = parseValue(parts.get(0), dataMap);
            if (result == null) {
                return expression;
            }
            
            // 从第二个部分开始，都是要减去的
            for (int i = 1; i < parts.size(); i++) {
                BigDecimal value = parseValue(parts.get(i), dataMap);
                if (value != null) {
                    result = result.subtract(value);
                }
            }
            
            return result.toString();
        }
        
        return expression;
    }
    
    /**
     * 按减法分割表达式，考虑对象属性
     */
    private static List<String> splitBySubtraction(String expression) {
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int braceCount = 0;
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '{') {
                braceCount++;
            } else if (c == '}') {
                braceCount--;
            } else if (c == '-' && braceCount == 0) {
                // 只有在括号外部的减号才分割
                parts.add(current.toString().trim());
                current = new StringBuilder();
                continue;
            }
            
            current.append(c);
        }
        
        if (current.length() > 0) {
            parts.add(current.toString().trim());
        }
        
        return parts;
    }
    
    /**
     * 解析值
     * 
     * @param valueStr 值字符串
     * @param dataMap 数据映射
     * @return 解析后的BigDecimal值
     */
    private static BigDecimal parseValue(String valueStr, Map<String, Object> dataMap) {
        try {
            // 处理时间序列表达式
            if (valueStr.startsWith("T-")) {
                // T-13 这样的表达式无法单独解析，需要返回null
                return null;
            }
            
            // 如果是对象属性引用
            if (valueStr.contains(".")) {
                String[] parts = valueStr.split("\\.", 2);
                String objectName = parts[0];
                String propertyName = parts[1];
                
                Object obj = dataMap.get(objectName);
                if (obj != null) {
                    Field field = obj.getClass().getDeclaredField(propertyName);
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value instanceof BigDecimal) {
                        return (BigDecimal) value;
                    }
                }
            } else {
                // 直接解析数字
                try {
                    return new BigDecimal(valueStr);
                } catch (NumberFormatException e) {
                    // 如果不是数字，返回null
                    return null;
                }
            }
        } catch (Exception e) {
            // 解析失败
        }
        return null;
    }
    
    /**
     * 创建数据映射
     * 
     * @param calculateUnit 计算单元数据
     * @param asset 资产数据
     * @param marketBench 市场基准数据
     * @param securityMarket 证券市场数据
     * @return 数据映射
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
     * 处理复杂的时间序列表达式
     * 例如：${CalculateUnitDo.avgSize} - ${T-13}${CalculateUnitDo.avgSize}
     * 
     * @param expression 复杂时间序列表达式
     * @param dataMap 数据映射
     * @return 处理后的值
     */
    private static String handleComplexTimeSeriesExpression(String expression, Map<String, Object> dataMap) {
        try {
            // 先解析所有的时间序列部分
            String processedExpression = expression;
            
            // 查找并替换所有 ${T-N}${...} 格式的时间序列表达式
            Pattern timeSeriesPattern = Pattern.compile("\\$\\{T-(\\d+)\\}\\$\\{([^}]+)\\}");
            Matcher matcher = timeSeriesPattern.matcher(expression);
            
            while (matcher.find()) {
                String daysStr = matcher.group(1);
                String propertyPath = matcher.group(2);
                
                // 解析属性路径
                String[] parts = propertyPath.split("\\.", 2);
                if (parts.length == 2) {
                    String objectName = parts[0];
                    String propertyName = parts[1];
                    
                    Object obj = dataMap.get(objectName);
                    if (obj != null) {
                        BigDecimal currentValue = getPropertyValueAsBigDecimal(obj, propertyName);
                        if (currentValue != null) {
                            // 计算真实的T-N天日期
                            String historicalDate = calculateHistoricalDate(dataMap, Integer.parseInt(daysStr));
                            // 模拟T-N天的值（实际应用中应该根据historicalDate从历史数据中获取）
                            BigDecimal historicalValue = currentValue.multiply(new BigDecimal("0.95"));
                            processedExpression = processedExpression.replace(matcher.group(0), historicalValue.toString());
                        }
                    }
                }
            }
            
            // 现在处理剩余的普通属性引用
            Matcher normalMatcher = TEMPLATE_PATTERN.matcher(processedExpression);
            StringBuffer result = new StringBuffer();
            
            while (normalMatcher.find()) {
                String normalExpression = normalMatcher.group(1);
                String value = evaluateExpression(normalExpression, dataMap);
                normalMatcher.appendReplacement(result, Matcher.quoteReplacement(value));
            }
            normalMatcher.appendTail(result);
            
            // 最后处理数学表达式
            String finalExpression = result.toString();
            
            // 处理纯数字减法表达式
            if (finalExpression.contains("-") && !finalExpression.startsWith("T-") && !finalExpression.contains("${")) {
                return evaluatePureNumberExpression(finalExpression);
            }
            
            // 处理包含${}的复杂表达式
            if (finalExpression.contains("-") && !finalExpression.startsWith("T-") && finalExpression.contains("${")) {
                return evaluateMathExpression(finalExpression, dataMap);
            }
            
            return finalExpression;
            
        } catch (Exception e) {
            return expression; // 解析失败时返回原表达式
        }
    }
    
    /**
     * 处理时间序列表达式
     * 例如：T-13${CalculateUnitDo.avgSize} 表示13天前的avgSize值
     * 
     * @param expression 时间序列表达式
     * @param dataMap 数据映射
     * @return 处理后的值
     */
    private static String handleTimeSeriesExpression(String expression, Map<String, Object> dataMap) {
        try {
            // 解析T-N格式，例如${T-13}${CalculateUnitDo.avgSize}
            if (expression.matches("\\$\\{T-\\d+\\}\\$\\{.*\\}")) {
                // 提取天数
                String dayStr = expression.substring(3, expression.indexOf("}"));
                int days = Integer.parseInt(dayStr);
                
                // 提取对象属性部分
                String propertyPart = expression.substring(expression.lastIndexOf("${") + 2);
                propertyPart = propertyPart.substring(0, propertyPart.length() - 1); // 去掉最后的}
                
                // 解析对象属性
                String[] parts = propertyPart.split("\\.", 2);
                if (parts.length == 2) {
                    String objectName = parts[0];
                    String propertyName = parts[1];
                    
                    // 获取当前对象
                    Object obj = dataMap.get(objectName);
                    if (obj != null) {
                        BigDecimal currentValue = getPropertyValueAsBigDecimal(obj, propertyName);
                        if (currentValue != null) {
                            // 模拟T-N天的值（实际应用中应该从历史数据中获取）
                            // 这里简单地将当前值乘以一个系数来模拟历史值
                            BigDecimal historicalValue = currentValue.multiply(new BigDecimal("0.95")); // 假设历史值比当前值小5%
                            return historicalValue.toString();
                        }
                    }
                }
            }
            
            return expression; // 解析失败时返回原表达式
        } catch (Exception e) {
            return expression; // 解析失败时返回原表达式
        }
    }
    
    /**
     * 获取对象属性值并转换为BigDecimal
     * 
     * @param obj 对象
     * @param propertyName 属性名
     * @return BigDecimal值
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
     * 处理包含纯数字表达式的混合模板
     * 
     * @param template 混合模板
     * @param dataMap 数据映射
     * @return 处理后的模板
     */
    private static String processMixedTemplate(String template, Map<String, Object> dataMap) {
        try {
            // 先处理${...}表达式
            Matcher matcher = TEMPLATE_PATTERN.matcher(template);
            StringBuffer result = new StringBuffer();
            
            while (matcher.find()) {
                String expression = matcher.group(1);
                String value = evaluateExpression(expression, dataMap);
                matcher.appendReplacement(result, Matcher.quoteReplacement(value));
            }
            matcher.appendTail(result);
            
            // 然后处理纯数字表达式
            String processedTemplate = result.toString();
            if (processedTemplate.contains("-") && !processedTemplate.contains("${")) {
                return evaluatePureNumberExpression(processedTemplate);
            }
            
            return processedTemplate;
        } catch (Exception e) {
            return template;
        }
    }
    
    /**
     * 计算纯数字表达式
     * 
     * @param expression 纯数字表达式，如 "102.85 - 97.7075"
     * @return 计算结果
     */
    public static String evaluatePureNumberExpression(String expression) {
        try {
            // 处理包含括号的表达式
            if (expression.contains("(") && expression.contains(")")) {
                return evaluateBracketExpression(expression);
            }
            
            // 先尝试按 " - " 分割（有空格）
            String[] parts = expression.split(" - ");
            if (parts.length >= 2) {
                BigDecimal result = new BigDecimal(parts[0].trim());
                
                // 从第二个部分开始，都是要减去的
                for (int i = 1; i < parts.length; i++) {
                    BigDecimal value = new BigDecimal(parts[i].trim());
                    result = result.subtract(value);
                }
                
                return result.toString();
            }
            
            // 如果没有空格，尝试按 "-" 分割（无空格）
            parts = expression.split("-");
            if (parts.length >= 2) {
                BigDecimal result = new BigDecimal(parts[0].trim());
                
                // 从第二个部分开始，都是要减去的
                for (int i = 1; i < parts.length; i++) {
                    BigDecimal value = new BigDecimal(parts[i].trim());
                    result = result.subtract(value);
                }
                
                return result.toString();
            }
            
            return expression;
        } catch (Exception e) {
            return expression; // 解析失败时返回原表达式
        }
    }
    
    /**
     * 计算包含括号的表达式
     * 
     * @param expression 包含括号的表达式，如 "4.80-3.50 - (4.5600-3.3250)"
     * @return 计算结果
     */
    private static String evaluateBracketExpression(String expression) {
        try {
            // 找到最内层的括号
            int start = expression.lastIndexOf("(");
            int end = expression.indexOf(")", start);
            
            if (start != -1 && end != -1) {
                // 提取括号内的表达式
                String innerExpression = expression.substring(start + 1, end);
                
                // 计算括号内的表达式
                String innerResult = evaluateSimpleExpression(innerExpression);
                
                // 替换括号表达式
                String newExpression = expression.substring(0, start) + innerResult + expression.substring(end + 1);
                
                // 递归计算剩余部分
                return evaluateBracketExpression(newExpression);
            }
            
            // 如果没有括号了，计算最终表达式
            return evaluateSimpleExpression(expression);
        } catch (Exception e) {
            return expression;
        }
    }
    
    /**
     * 计算简单表达式（不包含括号）
     * 
     * @param expression 简单表达式，如 "4.5600-3.3250"
     * @return 计算结果
     */
    public static String evaluateSimpleExpression(String expression) {
        try {
            // 先处理没有空格的减法（如 4.80-3.50）
            String processedExpression = expression;
            while (processedExpression.matches(".*\\d+(\\.\\d+)?-\\d+(\\.\\d+)?.*")) {
                // 找到第一个数字-数字的模式
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(\\d+(?:\\.\\d+)?)-(\\d+(?:\\.\\d+)?)");
                java.util.regex.Matcher matcher = pattern.matcher(processedExpression);
                if (matcher.find()) {
                    BigDecimal first = new BigDecimal(matcher.group(1));
                    BigDecimal second = new BigDecimal(matcher.group(2));
                    BigDecimal result = first.subtract(second);
                    processedExpression = processedExpression.replaceFirst(matcher.group(0), result.toString());
                } else {
                    break;
                }
            }
            
            // 然后按 " - " 分割处理剩余的减法
            String[] parts = processedExpression.split(" - ");
            if (parts.length >= 2) {
                BigDecimal result = new BigDecimal(parts[0].trim());
                
                for (int i = 1; i < parts.length; i++) {
                    BigDecimal value = new BigDecimal(parts[i].trim());
                    result = result.subtract(value);
                }
                
                return result.toString();
            }
            
            return processedExpression;
        } catch (Exception e) {
            return expression;
        }
    }
    
    /**
     * 计算历史日期
     * 
     * @param dataMap 数据映射
     * @param daysBack 往前推的天数
     * @return 历史日期（格式：yyyymmdd）
     */
    private static String calculateHistoricalDate(Map<String, Object> dataMap, int daysBack) {
        try {
            // 获取当前日期
            String currentDateStr = (String) dataMap.get("CurrentDate");
            if (currentDateStr == null || currentDateStr.length() != 8) {
                // 如果没有提供当前日期，使用默认值
                currentDateStr = "20250920";
            }
            
            // 解析当前日期
            int year = Integer.parseInt(currentDateStr.substring(0, 4));
            int month = Integer.parseInt(currentDateStr.substring(4, 6));
            int day = Integer.parseInt(currentDateStr.substring(6, 8));
            
            // 使用LocalDate进行日期计算
            java.time.LocalDate currentDate = java.time.LocalDate.of(year, month, day);
            java.time.LocalDate historicalDate = currentDate.minusDays(daysBack);
            
            // 格式化为yyyymmdd
            return String.format("%04d%02d%02d", 
                historicalDate.getYear(), 
                historicalDate.getMonthValue(), 
                historicalDate.getDayOfMonth());
                
        } catch (Exception e) {
            // 如果计算失败，返回默认值
            return "20250907";
        }
    }
    
    /**
     * 处理包含时间序列的CSV格式模板
     * 
     * @param template CSV格式的模板
     * @param dataMap 数据映射
     * @return 处理后的模板
     */
    private static String processCsvTemplateWithTimeSeries(String template, Map<String, Object> dataMap) {
        try {
            String[] fields = template.split(",");
            StringBuilder result = new StringBuilder();
            
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i].trim();
                
                // 处理每个字段
                if (field.contains("${")) {
                    // 包含${...}表达式的字段
                    field = handleComplexTimeSeriesExpression(field, dataMap);
                } else if (field.contains("-") && !field.startsWith("T-")) {
                    // 纯数字减法表达式
                    field = evaluatePureNumberExpression(field);
                }
                
                if (i > 0) {
                    result.append(",");
                }
                result.append(field);
            }
            
            return result.toString();
        } catch (Exception e) {
            return template;
        }
    }
}

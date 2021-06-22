package com.demo.io_jdbc.entity;

import java.util.List;

/**
 * @Auther: zwy
 * @Date: 2020/6/10
 * @Description: com.demo.jdbc.entity
 * @version:
 */
public class SqlData {

    // 数据量大小
    private Integer rowNum;

    // 定义字段类型，及对应的字段长度，以_作为分隔符，前段为数据类型，后段为字段长度。
    private List<String> columns;

    // 定义分隔符
    private String split_label;

    // 定义Double精度
    private Integer double_decimal;

    // 是否生成id
    private Boolean isId;

    // 生成id的方式
    private Integer type;


    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getSplit_label() {
        return split_label;
    }

    public void setSplit_label(String split_label) {
        this.split_label = split_label;
    }

    public Integer getDouble_decimal() {
        return double_decimal;
    }

    public void setDouble_decimal(Integer double_decimal) {
        this.double_decimal = double_decimal;
    }


    public Boolean getId() {
        return isId;
    }

    public void setId(Boolean id) {
        isId = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SqlData{" +
                "rowNum=" + rowNum +
                ", columns=" + columns +
                ", split_label='" + split_label + '\'' +
                ", double_decimal=" + double_decimal +
                ", isId=" + isId +
                ", type=" + type +
                '}';
    }
}

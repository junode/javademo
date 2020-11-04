package com.demo.io_jdbc.entity;

/**
 * @Auther: zwy
 * @Date: 2020/11/4
 * @Description: com.demo.io_jdbc.entity
 * @version:
 */
public class Table {
    private String system_id;
    private String table_code;
    private String table_name;
    private String table_comment;
    private String table_space;

    public String getSystem_id() {
        return system_id;
    }
    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }
    public String getTable_code() {
        return table_code;
    }
    public void setTable_code(String table_code) {
        this.table_code = table_code;
    }
    public String getTable_name() {
        return table_name;
    }
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    public String getTable_comment() {
        return table_comment;
    }
    public void setTable_comment(String table_comment) {
        this.table_comment = table_comment;
    }
    public String getTable_space() {
        return table_space;
    }
    public void setTable_space(String table_space) {
        this.table_space = table_space;
    }
}

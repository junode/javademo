package com.demo.io_jdbc.entity;

/**
 * @Auther: zwy
 * @Date: 2020/11/4
 * @Description: com.demo.io_jdbc.entity
 * @version:
 */
public class TableColumn {

    private String system_id;
    private String table_code;
    private String column_code;
    private String column_name;
    private String column_default;
    private String column_primary;
    private String column_mandatory;
    private String column_dict;
    private String column_id;
    private String column_comment;
    private String column_length;
    private String column_precision;
    private String column_data_type;

    public String getColumn_data_type() {
        return column_data_type;
    }
    public void setColumn_data_type(String column_data_type) {
        this.column_data_type = column_data_type;
    }
    public void setColumn_length(String column_length) {
        this.column_length = column_length;
    }
    public void setColumn_precision(String column_precision) {
        this.column_precision = column_precision;
    }



    public String getColumn_length() {
        return column_length;
    }
    public String getColumn_precision() {
        return column_precision;
    }
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
    public String getColumn_code() {
        return column_code;
    }
    public void setColumn_code(String column_code) {
        this.column_code = column_code;
    }
    public String getColumn_name() {
        return column_name;
    }
    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }
    public String getColumn_default() {
        return column_default;
    }
    public void setColumn_default(String column_default) {
        this.column_default = column_default;
    }
    public String getColumn_primary() {
        return column_primary;
    }
    public void setColumn_primary(String column_primary) {
        this.column_primary = column_primary;
    }
    public String getColumn_mandatory() {
        return column_mandatory;
    }
    public void setColumn_mandatory(String column_mandatory) {
        this.column_mandatory = column_mandatory;
    }
    public String getColumn_dict() {
        return column_dict;
    }
    public void setColumn_dict(String column_dict) {
        this.column_dict = column_dict;
    }
    public String getColumn_id() {
        return column_id;
    }
    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }
    public String getColumn_comment() {
        return column_comment;
    }
    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }

}

package com.dt.common.constant;

/**
 * Created with IntelliJ IDEA.
 * Description:日志类型枚举
 * User: fan xiao chun
 * Date: 2018-07-27
 * Time: 11:52
 */
public enum LogTypeEnum {

    USER_LOG(1, "用户");

    LogTypeEnum(Integer typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    private String typeName;
    private Integer typeCode;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }
}

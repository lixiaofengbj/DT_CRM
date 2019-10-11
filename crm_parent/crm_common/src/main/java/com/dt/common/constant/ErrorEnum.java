package com.dt.common.constant;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 14:13
 */
public enum ErrorEnum {

    SUCCESS(0, "success"),
    ERROR(500, "服务异常"),
    COMMUNICATION_ERROR(600, "通信异常");

    private Integer errorCode;
    private String errorMessage;

    ErrorEnum(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

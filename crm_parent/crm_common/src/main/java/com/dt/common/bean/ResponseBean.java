package com.dt.common.bean;

import com.dt.common.constant.ErrorEnum;
import com.dt.common.utils.CommonUtils;

import java.io.Serializable;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 14:10
 */
public class ResponseBean implements Serializable {

    private Integer errorCode = ErrorEnum.SUCCESS.getErrorCode();

    private String errorMessage = ErrorEnum.SUCCESS.getErrorMessage();

    private Object data;

    private ErrorEnum errorEnum;

    public ResponseBean(Object data) {
        this.data = data;
    }

    public ResponseBean(Integer errorCode, String errorMessage, Object data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public ResponseBean(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public ResponseBean() {
    }

    public ResponseBean(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public Integer getErrorCode() {
        if (CommonUtils.isNotNull(this.errorEnum)) {
            return this.errorEnum.getErrorCode();
        }
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        if (CommonUtils.isNotNull(this.errorEnum)) {
            return this.errorEnum.getErrorMessage();
        }
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

package com.dt.pojo;

import javax.persistence.Column;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 10:50
 */
public class CrmUserVo extends CrmUser {

    private Integer id;
    private String userName;
    private String password;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}

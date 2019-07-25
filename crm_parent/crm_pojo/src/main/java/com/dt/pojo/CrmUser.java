package com.dt.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: lixiaofeng
 * @Date: 2019/3/5 10:49
 */
@Table(name = "crm_user")
public class CrmUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    private String passeord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasseord() {
        return passeord;
    }

    public void setPasseord(String passeord) {
        this.passeord = passeord;
    }

    public CrmUser() {
    }
}

package com.dt.pojo;

import javax.persistence.*;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/1 16:01
 */
@Table(name = "crm_role")
public class CrmRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;
    private Integer pid;
    @Column(name = "role_name")
    private String roleName;
    private String synopsis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}

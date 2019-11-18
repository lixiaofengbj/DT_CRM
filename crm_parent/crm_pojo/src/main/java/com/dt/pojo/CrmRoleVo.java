package com.dt.pojo;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/1 16:01
 */
public class CrmRoleVo {

    private Integer id;
    private Integer pid;
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

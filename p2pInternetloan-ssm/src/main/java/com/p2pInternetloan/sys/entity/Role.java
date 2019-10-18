package com.p2pInternetloan.sys.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -13244021713413817L;
    //角色ID,主键
    private Integer roleid;
    //角色名称
    private String rolename;
    //角色描述
    private String description;

    
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
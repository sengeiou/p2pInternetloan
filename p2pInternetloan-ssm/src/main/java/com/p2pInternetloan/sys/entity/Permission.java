package com.p2pInternetloan.sys.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -53726900980994679L;
    //ID,主键
    private Integer perid;
    //权限名称
    private String pername;
    //父编号
    private Integer pid;
    //权限字符串：例如：system:user:create:1
    private String permission;

    
    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }
    
    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }
    
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
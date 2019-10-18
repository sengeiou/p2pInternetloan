package com.p2pInternetloan.sys.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 686361265383481326L;
    //角色ID
    private Integer roleid;
    //权限ID
    private Integer perid;

    
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }

}
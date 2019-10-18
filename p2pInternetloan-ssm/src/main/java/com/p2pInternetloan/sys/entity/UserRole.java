package com.p2pInternetloan.sys.entity;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 151921863204190324L;
    //用户ID
    private Integer userid;
    //角色ID
    private Integer roleid;

    
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

}
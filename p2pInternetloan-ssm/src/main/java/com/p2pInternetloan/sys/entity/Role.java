package com.p2pInternetloan.sys.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2019-10-20 08:15:41
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -68283677249379469L;
    //角色ID,主键
    private Integer roleId;
    //角色名称
    private String roleName;
    //角色描述
    private String roleDesc;
    //角色标识: 0 不可用 1 可用
    private Integer roleFlag;


    
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    
    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }
}
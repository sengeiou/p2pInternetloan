package com.p2pInternetloan.borrowing.entity;

import java.io.Serializable;

/**
 * (LoginLog)实体类
 *
 * @author makejava
 * @since 2019-10-23 21:09:53
 */
public class LoginLog implements Serializable {
    private static final long serialVersionUID = -47426638297814675L;
    //id

    private Long logId;
    //用户id

    private Long userId;
    //用户姓名

    private String userName;
    //用户角色

    private String userRole;
    //日志描述

    private String content;
    //日志类型(字典表数据)

    private String 
type;
    //参数集合

    private String remarks;
    //表格名称

    private String tablename;
    //返回值

    private String resultValue;
    //ip地址

    private String ip;
    //请求地址

    private String requestUrl;
    //操作结果(字典表数据)

    private Integer result;
    //错误信息

    private String exString;

    
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String get
type() {
        return 
type;
    }

    public void set
type(String 
type) {
        this.
type = 
type;
    }
    
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
    
    public String getExString() {
        return exString;
    }

    public void setExString(String exString) {
        this.exString = exString;
    }

}
package com.p2pInternetloan.sys.entity;

import java.util.List;

/**
 * @Description: 菜单对象
 * @Author: cpc
 * @Date: 2019-10-19 21:21
 * @Version: V1.0
 */
public class Menu {
    //ID,主键
    private Integer perId;
    //权限名称
    private String perName;
    //父编号
    private Integer pid;
    //菜单图标
    private String icon;
    //请求路径 可为空
    private String url;
    //显示排序
    private Integer sort;
    //权限类 菜单 按钮
    private Integer type;
    //说明
    private String mark;

    //这是儿子们
    private List<Menu> childern;

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<Menu> getChildern() {
        return childern;
    }

    public void setChildern(List<Menu> childern) {
        this.childern = childern;
    }
}

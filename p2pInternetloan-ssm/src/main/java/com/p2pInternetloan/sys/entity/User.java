package com.p2pInternetloan.sys.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
public class User implements Serializable {
    private static final long serialVersionUID = 983565716729777914L;
    //用户ID,主键
    private Integer userid;
    //用户账号
    private String username;
    //用户密码=MD5+盐加密
    private String password;
    //盐
    private String salt;
    //创建日期
    private Date createdate;

    
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}
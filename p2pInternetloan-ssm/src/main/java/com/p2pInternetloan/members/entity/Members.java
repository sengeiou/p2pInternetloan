package com.p2pInternetloan.members.entity;

import java.io.Serializable;

/**
 * (Members)实体类
 *
 * @author makejava
 * @since 2019-10-23 14:21:23
 */
public class Members implements Serializable {
    private static final long serialVersionUID = -61709004424700033L;
    //id
    private Long id;
    //用户名

    private String name;
    //密码

    private String password;
    //认证分数
    private Integer certificationScore;
    //是否身份认证(0 yes 1 no 默认为1)

    private Integer isIdentityAuthentication;
    //是否可用(0 可用，1 不可用)

    private Integer membersState;
    //活期日利率（待删除）
    private Double rate;
    //
//活期日获利时间
    private Object rateTime;
    //bid_request_id
    private Long bidRequestId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getCertificationScore() {
        return certificationScore;
    }

    public void setCertificationScore(Integer certificationScore) {
        this.certificationScore = certificationScore;
    }
    
    public Integer getIsIdentityAuthentication() {
        return isIdentityAuthentication;
    }

    public void setIsIdentityAuthentication(Integer isIdentityAuthentication) {
        this.isIdentityAuthentication = isIdentityAuthentication;
    }
    
    public Integer getMembersState() {
        return membersState;
    }

    public void setMembersState(Integer membersState) {
        this.membersState = membersState;
    }
    
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
    
    public Object getRateTime() {
        return rateTime;
    }

    public void setRateTime(Object rateTime) {
        this.rateTime = rateTime;
    }
    
    public Long getBidRequestId() {
        return bidRequestId;
    }

    public void setBidRequestId(Long bidRequestId) {
        this.bidRequestId = bidRequestId;
    }

}
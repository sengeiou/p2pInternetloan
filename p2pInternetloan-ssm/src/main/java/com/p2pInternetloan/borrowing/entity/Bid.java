package com.p2pInternetloan.borrowing.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Bid)实体类
 *
 * @author makejava
 * @since 2019-10-23 15:03:53
 */
public class Bid implements Serializable {
    private static final long serialVersionUID = -85177407322026486L;
    //id
    private Long id;
    //实际利率
    private Double actualRate;
    //投标额度
    private Double availableAmount;
    //所投标的id

    private Long bidRequestId;
    //投标会员id

    private Long membersId;
    //投标时间
    private Date bidTime;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getActualRate() {
        return actualRate;
    }

    public void setActualRate(Double actualRate) {
        this.actualRate = actualRate;
    }
    
    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }
    
    public Long getBidRequestId() {
        return bidRequestId;
    }

    public void setBidRequestId(Long bidRequestId) {
        this.bidRequestId = bidRequestId;
    }
    
    public Long getMembersId() {
        return membersId;
    }

    public void setMembersId(Long membersId) {
        this.membersId = membersId;
    }
    
    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

}
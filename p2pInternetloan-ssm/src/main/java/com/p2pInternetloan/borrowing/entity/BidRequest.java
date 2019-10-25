package com.p2pInternetloan.borrowing.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (BidRequest)实体类
 *
 * @author makejava
 * @since 2019-10-23 15:13:05
 */
public class BidRequest implements Serializable {
    private static final long serialVersionUID = 641060597359733060L;
    //id
    private Long id;
    //还款方式
//(0 一次付清 ，1 等额本息 ，2 等额本金)

    private Integer returnType;
    //贷款类型
//(0 信用贷,1 车贷,2 房贷)

    private Integer bidRequestType;
    //贷款状态
//（0 审核通过，1 审核失败，2 审核中，3 满标 ，4 还款中，5 逾期，6 投标超时、7 完成）

    private Integer bidRequestState;
    //贷款总额
    private Double bidRequestAmount;
    //贷款的年化利率

    private Double currentRate;
    //还款月数

    private Integer monthesReturn;
    //当前投标数量 冗余数量

    private Integer bidCount;
    //总利息

    private Double totalRewardAmount;
    //当前收到的投资总额
    private Double currentSum;
    //借款标题

    private String title;
    //借款描述

    private String description;
    //贷款安全信息
    private String note;
    //投标截止日期

    private Object disableDate;
    //借款人id

    private Long membersId;
    //发标时间天数
    private Integer disableDays;
    //发布时间

    private Date publishTime;
    //备注
    private String remark;
    //审核时间

    private Date auditTime;
    //申请时间
    private Date applyTime;
    //审核人

    private Long auditorId;
    //申请人id

    private Long applierId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }
    
    public Integer getBidRequestType() {
        return bidRequestType;
    }

    public void setBidRequestType(Integer bidRequestType) {
        this.bidRequestType = bidRequestType;
    }
    
    public Integer getBidRequestState() {
        return bidRequestState;
    }

    public void setBidRequestState(Integer bidRequestState) {
        this.bidRequestState = bidRequestState;
    }
    
    public Double getBidRequestAmount() {
        return bidRequestAmount;
    }

    public void setBidRequestAmount(Double bidRequestAmount) {
        this.bidRequestAmount = bidRequestAmount;
    }
    
    public Double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(Double currentRate) {
        this.currentRate = currentRate;
    }
    
    public Integer getMonthesReturn() {
        return monthesReturn;
    }

    public void setMonthesReturn(Integer monthesReturn) {
        this.monthesReturn = monthesReturn;
    }
    
    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }
    
    public Double getTotalRewardAmount() {
        return totalRewardAmount;
    }

    public void setTotalRewardAmount(Double totalRewardAmount) {
        this.totalRewardAmount = totalRewardAmount;
    }
    
    public Double getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(Double currentSum) {
        this.currentSum = currentSum;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public Object getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Object disableDate) {
        this.disableDate = disableDate;
    }
    
    public Long getMembersId() {
        return membersId;
    }

    public void setMembersId(Long membersId) {
        this.membersId = membersId;
    }
    
    public Integer getDisableDays() {
        return disableDays;
    }

    public void setDisableDays(Integer disableDays) {
        this.disableDays = disableDays;
    }
    
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    
    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }
    
    public Long getApplierId() {
        return applierId;
    }

    public void setApplierId(Long applierId) {
        this.applierId = applierId;
    }

}
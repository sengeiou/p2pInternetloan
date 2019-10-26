package com.p2pInternetloan.assets.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (MembersAccount)实体类
 *
 * @author makejava
 * @since 2019-10-24 19:33:38
 */
public class MembersAccount implements Serializable {
    private static final long serialVersionUID = 864134310460874480L;
    //id
    private Integer id;
    //会员id

    private Integer membersId;
    //交易密码

    private String tradePassword;
    //账户可用余额

    private String usableAmount;
    //账户待收利息

    private Date unReceiveInterest;
    //账户代收本金

    private Date unReceivePrincipal;
    //账户授信额度(初始1000)
    private Date borrowLimit;
    //支付宝唯一标识
    private String tradeCode;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }
    
    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
    
    public String getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(String usableAmount) {
        this.usableAmount = usableAmount;
    }
    
    public Date getUnReceiveInterest() {
        return unReceiveInterest;
    }

    public void setUnReceiveInterest(Date unReceiveInterest) {
        this.unReceiveInterest = unReceiveInterest;
    }
    
    public Date getUnReceivePrincipal() {
        return unReceivePrincipal;
    }

    public void setUnReceivePrincipal(Date unReceivePrincipal) {
        this.unReceivePrincipal = unReceivePrincipal;
    }
    
    public Date getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(Date borrowLimit) {
        this.borrowLimit = borrowLimit;
    }
    
    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

}
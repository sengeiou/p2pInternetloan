package com.p2pInternetloan.members.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (MembersRealname)实体类
 *
 * @author makejava
 * @since 2019-10-23 14:21:24
 */
public class MembersRealname implements Serializable {
    private static final long serialVersionUID = 698576575517237251L;
    //id
    private Long id;
    //真实姓名
    private String realname;
    //性别

    private String sex;
    //出生日期
    private Date bornDate;
    //身份证号码
    private String idNumber;
    //证件地址
    private String address;
    //状态（0审核成功，1待审核，2审核失败)
    private Object state;
    //身份证正面图片路径
    private String image1;
    //身份证反面图片路径
    private String image2;
    //备注
    private String remark;
    //审核时间

    private Date auditTime;
    //申请时间
    private Date applyTime;
    //审核人id
    private Long auditorId;
    //申请人id

    private Long membersId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
    
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }
    
    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }
    
    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
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
    
    public Long getMembersId() {
        return membersId;
    }

    public void setMembersId(Long membersId) {
        this.membersId = membersId;
    }

}
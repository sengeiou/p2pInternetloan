package com.p2pInternetloan.borrowing.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (MembersMaterials)实体类
 *
 * @author makejava
 * @since 2019-10-23 15:03:16
 */
public class MembersMaterials implements Serializable {
    private static final long serialVersionUID = -37826671289346695L;
    //id
    private Long id;
    //审核状态（0审核成功，1待审核，2审核失败）
    private Integer state;
    //审核说明

    private String remark;
    //审核时间
    private Date auditTime;
    //申请时间
    private Date applyTime;
    //审核人id
    private Long auditorId;
    //申请人id
    private Long membersId;
    //材料得分

    private Integer score;
    //文件路径
    private String file;
    //图片路径
    private String image;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
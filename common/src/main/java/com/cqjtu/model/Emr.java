package com.cqjtu.model;

import java.util.Date;

public class Emr {
    private String emrId;

    private Date createTime;

    private Integer createAge;

    private String idCard;

    public String getEmrId() {
        return emrId;
    }

    public void setEmrId(String emrId) {
        this.emrId = emrId == null ? null : emrId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateAge() {
        return createAge;
    }

    public void setCreateAge(Integer createAge) {
        this.createAge = createAge;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }
}
package com.cqjtu.model;

import java.util.Date;

public class Emr {
    private String emrId;

    private Date createTime;

    private Integer createAge;

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
}
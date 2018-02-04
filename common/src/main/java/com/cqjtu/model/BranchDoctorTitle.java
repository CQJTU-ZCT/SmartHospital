package com.cqjtu.model;

public class BranchDoctorTitle {
    private Integer bdtId;

    private String idCard;

    private Integer titleId;

    private Integer branchId;

    public Integer getBdtId() {
        return bdtId;
    }

    public void setBdtId(Integer bdtId) {
        this.bdtId = bdtId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }
}
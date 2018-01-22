package com.cqjtu.model;

public class BranchDoctorTitle {
    private Integer id;

    private String idCard;

    private Short titleId;

    private Integer branchId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Short getTitleId() {
        return titleId;
    }

    public void setTitleId(Short titleId) {
        this.titleId = titleId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }
}
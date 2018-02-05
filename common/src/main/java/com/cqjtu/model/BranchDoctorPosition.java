package com.cqjtu.model;

public class BranchDoctorPosition {
    private Integer bdpId;

    private String idCard;

    private Integer branchId;

    private Integer positionId;

    public Integer getBdpId() {
        return bdpId;
    }

    public void setBdpId(Integer bdpId) {
        this.bdpId = bdpId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
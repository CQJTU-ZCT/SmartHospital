package com.cqjtu.model;

public class ExclusiveDoctor {
    private String exclusiveDoctorId;

    private String idCard;

    private Integer statusId;

    private String useIdCard;

    public String getExclusiveDoctorId() {
        return exclusiveDoctorId;
    }

    public void setExclusiveDoctorId(String exclusiveDoctorId) {
        this.exclusiveDoctorId = exclusiveDoctorId == null ? null : exclusiveDoctorId.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getUseIdCard() {
        return useIdCard;
    }

    public void setUseIdCard(String useIdCard) {
        this.useIdCard = useIdCard == null ? null : useIdCard.trim();
    }
}
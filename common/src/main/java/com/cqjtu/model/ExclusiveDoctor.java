package com.cqjtu.model;

public class ExclusiveDoctor {
    private String exclusiveDoctorId;

    private String doctorIdCard;

    private Integer statusId;

    private String userIdCard;

    public String getExclusiveDoctorId() {
        return exclusiveDoctorId;
    }

    public void setExclusiveDoctorId(String exclusiveDoctorId) {
        this.exclusiveDoctorId = exclusiveDoctorId == null ? null : exclusiveDoctorId.trim();
    }

    public String getDoctorIdCard() {
        return doctorIdCard;
    }

    public void setDoctorIdCard(String doctorIdCard) {
        this.doctorIdCard = doctorIdCard == null ? null : doctorIdCard.trim();
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }
}
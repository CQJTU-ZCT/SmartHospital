package com.cqjtu.model;

import java.util.Date;

public class UsersDetail {
    private String idCard;

    private String address;

    private Short sex;

    private String profile;

    private Date birthYMD;

    private String nationality;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public Date getBirthYMD() {
        return birthYMD;
    }

    public void setBirthYMD(Date birthYMD) {
        this.birthYMD = birthYMD;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }
}
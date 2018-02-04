package com.cqjtu.model;

public class Nation {
    private Integer nationId;

    private String nationDesc;

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getNationDesc() {
        return nationDesc;
    }

    public void setNationDesc(String nationDesc) {
        this.nationDesc = nationDesc == null ? null : nationDesc.trim();
    }
}
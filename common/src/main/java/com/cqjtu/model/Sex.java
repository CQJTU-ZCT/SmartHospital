package com.cqjtu.model;

public class sex {
    private Integer sexId;

    private String sexDesc;

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc == null ? null : sexDesc.trim();
    }
}
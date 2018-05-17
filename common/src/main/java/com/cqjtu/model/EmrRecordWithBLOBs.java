package com.cqjtu.model;

public class EmrRecordWithBLOBs extends EmrRecord {
    private String description;

    private String treament;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTreament() {
        return treament;
    }

    public void setTreament(String treament) {
        this.treament = treament == null ? null : treament.trim();
    }
}
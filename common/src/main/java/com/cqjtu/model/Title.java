package com.cqjtu.model;

public class Title {
    private Short titleId;

    private String name;

    public Short getTitleId() {
        return titleId;
    }

    public void setTitleId(Short titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
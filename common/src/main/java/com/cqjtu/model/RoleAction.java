package com.cqjtu.model;

public class RoleAction {
    private Integer roleAccessId;

    private Integer roleId;

    private String accessDescription;

    public Integer getRoleAccessId() {
        return roleAccessId;
    }

    public void setRoleAccessId(Integer roleAccessId) {
        this.roleAccessId = roleAccessId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAccessDescription() {
        return accessDescription;
    }

    public void setAccessDescription(String accessDescription) {
        this.accessDescription = accessDescription == null ? null : accessDescription.trim();
    }
}
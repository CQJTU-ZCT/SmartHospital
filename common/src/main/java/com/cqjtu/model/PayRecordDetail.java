package com.cqjtu.model;

public class PayRecordDetail {
    private String payOrderId;

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }
}
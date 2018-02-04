package com.cqjtu.model;

import java.math.BigDecimal;
import java.util.Date;

public class PreOrders {
    private String orderId;

    private Integer appointmentStatusId;

    private Date createTime;

    private Date cancleTime;

    private Date finishTime;

    private BigDecimal price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCancleTime() {
        return cancleTime;
    }

    public void setCancleTime(Date cancleTime) {
        this.cancleTime = cancleTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
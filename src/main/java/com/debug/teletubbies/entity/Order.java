package com.debug.teletubbies.entity;

import java.math.BigDecimal;

public class Order {
    private Integer orderId;

    private Integer userId;

    private Integer contentId;

    private BigDecimal cost;

    private Integer statusId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Order(Integer userId, Integer contentId, BigDecimal cost, Integer statusId) {
        this.userId = userId;
        this.contentId = contentId;
        this.cost = cost;
        this.statusId = statusId;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", contentId=" + contentId +
                ", cost=" + cost +
                ", statusId=" + statusId +
                '}';
    }
}
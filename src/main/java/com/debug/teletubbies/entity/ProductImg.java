package com.debug.teletubbies.entity;

public class ProductImg {
    private Integer flowerImgId;

    private Integer flowerId;

    private String flowerImgPath;

    public Integer getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerImgPath() {
        return flowerImgPath;
    }

    public void setFlowerImgPath(String flowerImgPath) {
        this.flowerImgPath = flowerImgPath == null ? null : flowerImgPath.trim();
    }
    public Integer getFlowerImgId() {
        return flowerImgId;
    }

    public void setFlowerImgId(Integer flowerImgId) {
        this.flowerImgId = flowerImgId;
    }
}
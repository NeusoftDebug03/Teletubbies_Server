package com.debug.teletubbies.entity;

import java.math.BigDecimal;

public class Product {
    private Integer flowerId;

    private String flowerName;

    private String flowerLangs;

    private String flowerIntro;

    private String flowerDetails;

    private String flowerExtraInfo;

    private BigDecimal flowerPrice;

    private BigDecimal flowerDiscount;

    private Integer flowerStock;

    private String flowerClass;

    public String getFlowerClass() {
        return flowerClass;
    }

    public void setFlowerClass(String flowerClass) {
        this.flowerClass = flowerClass;
    }

    public Integer getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName == null ? null : flowerName.trim();
    }

    public String getFlowerLangs() {
        return flowerLangs;
    }

    public void setFlowerLangs(String flowerLangs) {
        this.flowerLangs = flowerLangs == null ? null : flowerLangs.trim();
    }

    public String getFlowerIntro() {
        return flowerIntro;
    }

    public void setFlowerIntro(String flowerIntro) {
        this.flowerIntro = flowerIntro == null ? null : flowerIntro.trim();
    }

    public String getFlowerDetails() {
        return flowerDetails;
    }

    public void setFlowerDetails(String flowerDetails) {
        this.flowerDetails = flowerDetails == null ? null : flowerDetails.trim();
    }

    public String getFlowerExtraInfo() {
        return flowerExtraInfo;
    }

    public void setFlowerExtraInfo(String flowerExtraInfo) {
        this.flowerExtraInfo = flowerExtraInfo == null ? null : flowerExtraInfo.trim();
    }

    public BigDecimal getFlowerPrice() {
        return flowerPrice;
    }

    public void setFlowerPrice(BigDecimal flowerPrice) {
        this.flowerPrice = flowerPrice;
    }

    public BigDecimal getFlowerDiscount() {
        return flowerDiscount;
    }

    public void setFlowerDiscount(BigDecimal flowerDiscount) {
        this.flowerDiscount = flowerDiscount;
    }

    public Integer getFlowerStock() {
        return flowerStock;
    }

    public void setFlowerStock(Integer flowerStock) {
        this.flowerStock = flowerStock;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "flowerId=" + flowerId +
                ", flowerName='" + flowerName + '\'' +
                ", flowerLangs='" + flowerLangs + '\'' +
                ", flowerIntro='" + flowerIntro + '\'' +
                ", flowerDetails='" + flowerDetails + '\'' +
                ", flowerExtraInfo='" + flowerExtraInfo + '\'' +
                ", flowerPrice=" + flowerPrice +
                ", flowerDiscount=" + flowerDiscount +
                ", flowerStock=" + flowerStock +
                '}';
    }
}
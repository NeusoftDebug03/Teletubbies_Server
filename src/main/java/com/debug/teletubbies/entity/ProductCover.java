package com.debug.teletubbies.entity;

import java.math.BigDecimal;

public class ProductCover {
    private Integer flowerId;
    private String flowerName;
    private BigDecimal flowerPrice;
    private String flowerImgPath;

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
        this.flowerName = flowerName;
    }

    public BigDecimal getFlowerPrice() {
        return flowerPrice;
    }

    public void setFlowerPrice(BigDecimal flowerPrice) {
        this.flowerPrice = flowerPrice;
    }

    public String getFlowerImgPath() {
        return flowerImgPath;
    }

    public void setFlowerImgPath(String flowerImgPath) {
        this.flowerImgPath = flowerImgPath;
    }
}

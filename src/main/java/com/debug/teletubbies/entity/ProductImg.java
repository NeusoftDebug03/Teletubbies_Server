package com.debug.teletubbies.entity;

public class ProductImg {
    private Integer productImgId;

    private Integer productId;

    private String productImgPath;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImgPath() {
        return productImgPath;
    }

    public void setProductImgPath(String productImgPath) {
        this.productImgPath = productImgPath == null ? null : productImgPath.trim();
//        this.productImgPath = productImgPath;
    }

    public Integer getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Integer productImgId) {
        this.productImgId = productImgId;
    }
}
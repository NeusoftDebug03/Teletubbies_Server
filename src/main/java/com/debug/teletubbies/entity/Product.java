package com.debug.teletubbies.entity;

import java.math.BigDecimal;

public class Product {
    private Integer productId;

    private String productName;

    private String productLangs;

    private String productIntro;

    private String productDetails;

    private String productExtraInfo;

    private BigDecimal productPrice;

    private BigDecimal productDiscount;

    private Integer productStock;

    private String productClass;

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductLangs() {
        return productLangs;
    }

    public void setProductLangs(String productLangs) {
        this.productLangs = productLangs == null ? null : productLangs.trim();
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro == null ? null : productIntro.trim();
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails == null ? null : productDetails.trim();
    }

    public String getProductExtraInfo() {
        return productExtraInfo;
    }

    public void setProductExtraInfo(String productExtraInfo) {
        this.productExtraInfo = productExtraInfo == null ? null : productExtraInfo.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productLangs='" + productLangs + '\'' +
                ", productIntro='" + productIntro + '\'' +
                ", productDetails='" + productDetails + '\'' +
                ", productExtraInfo='" + productExtraInfo + '\'' +
                ", productPrice=" + productPrice +
                ", productDiscount=" + productDiscount +
                ", productStock=" + productStock +
                '}';
    }
}
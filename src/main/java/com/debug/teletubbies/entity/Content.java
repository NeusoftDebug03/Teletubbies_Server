package com.debug.teletubbies.entity;

public class Content {
    private Integer contentId;

    private Integer productId;

    private Integer count;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Content(Integer contentId, Integer productId, Integer count) {
        this.contentId = contentId;
        this.productId = productId;
        this.count = count;
    }

    public Content() {
    }
}
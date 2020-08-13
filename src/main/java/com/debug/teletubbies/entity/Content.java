package com.debug.teletubbies.entity;

public class Content {
    private Integer contentId;

    private Integer flowerId;

    private Integer count;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Content(Integer contentId, Integer flowerId, Integer count) {
        this.contentId = contentId;
        this.flowerId = flowerId;
        this.count = count;
    }

    public Content() {
    }
}
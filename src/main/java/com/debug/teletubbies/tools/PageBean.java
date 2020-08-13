package com.debug.teletubbies.tools;

import java.util.List;

public class PageBean<T> {
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    private long totalNum;
    private Boolean isMore;
    private Integer totalPage;
    private List<T> items;

    public PageBean(Integer currentPage, Integer pageSize, long totalNum, Boolean isMore, Integer totalPage, List<T> items) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.isMore = isMore;
        this.totalPage = totalPage;
        this.items = items;
    }

    public PageBean() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public Boolean getMore() {
        return isMore;
    }

    public void setMore(Boolean more) {
        isMore = more;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

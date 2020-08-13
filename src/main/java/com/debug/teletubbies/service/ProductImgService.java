package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.ProductImg;
import com.debug.teletubbies.tools.PageBean;

import java.util.List;

public interface ProductImgService {
    List<ProductImg> getFlowerImgsById(Integer flowerId);

    void addFlowerImg(Integer flowerId, String imgPath);

    void delFlowerImg(ProductImg productImg);

    PageBean queryFlowersImgs(Integer currentPage, Integer pageSize);
}

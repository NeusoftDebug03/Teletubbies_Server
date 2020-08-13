package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.ProductImg;
import com.debug.teletubbies.tools.PageBean;

import java.util.List;

public interface ProductImgService {
    List<ProductImg> getProductImgsById(Integer productId);

    void addProductImg(Integer productId, String imgPath);

    void delProductImg(ProductImg productImg);

    PageBean queryProductsImgs(Integer currentPage, Integer pageSize);
}

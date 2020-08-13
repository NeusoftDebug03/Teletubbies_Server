package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.ProductImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImgMapper {
    int insert(ProductImg record);

    int update(ProductImg record);

    int insertSelective(ProductImg record);

    List<ProductImg> getProductImgsById(Integer productId);

    void delProductImg(ProductImg productImg);
}
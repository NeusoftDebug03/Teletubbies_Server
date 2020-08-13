package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.ProductCover;
import com.debug.teletubbies.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer flowerId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer flowerId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findAllFlowers();

    List<Product> findFlowersByKey(@Param("key") String key);

    List<Product> queryFlowers(Product condition);

    List<ProductCover> queryFlowerCovers(Product condition);

    String getFlowerCover(Integer flowerId);

    List<String> getFlowerImgs(Integer flowerId);

    List<ProductCover> queryFlowerCoversByKey(@Param("key") String key);
}
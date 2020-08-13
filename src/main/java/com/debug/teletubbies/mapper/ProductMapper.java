package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.ProductCover;
import com.debug.teletubbies.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findAllProducts();

    List<Product> findProductsByKey(@Param("key") String key);

    List<Product> queryProducts(Product condition);

    List<ProductCover> queryProductCovers(Product condition);

    String getProductCover(Integer productId);

    List<String> getProductImgs(Integer productId);

    List<ProductCover> queryProductCoversByKey(@Param("key") String key);
}
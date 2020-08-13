package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.Product;
import com.debug.teletubbies.tools.PageBean;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> findAllProducts();

    Product inserProduct(Product product);

    Integer modifyProduct(Product product);

    Integer deleteProduct(Integer productId);

    Product findProduct(Integer productId);



    PageBean queryProducts(Integer currentPage, Integer pageSize, Product condition);


    PageBean findProductsByKey(Integer currentPage, Integer pageSize, String key);

    PageBean queryProductCover(Integer currentPage, Integer pageSize, Product condition);

    Map<String,Object> getProductDetail(Integer productId);

    PageBean queryProductCoverByKey(Integer currentPage, Integer pageSize, String key);
}

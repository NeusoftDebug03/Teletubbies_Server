package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.Product;
import com.debug.teletubbies.tools.PageBean;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> findAllFlowers();

    Product inserFlower(Product product);

    Integer modifyFlower(Product product);

    Integer deleteFlower(Integer flowerId);

    Product findFlower(Integer flowerId);



    PageBean queryFlowers(Integer currentPage, Integer pageSize, Product condition);


    PageBean findFlowersByKey(Integer currentPage, Integer pageSize, String key);

    PageBean queryFlowerCover(Integer currentPage, Integer pageSize, Product condition);

    Map<String,Object> getFlowerDetail(Integer flowerId);

    PageBean queryFlowerCoverByKey(Integer currentPage, Integer pageSize, String key);
}

package com.debug.teletubbies.service.impl;

import com.debug.teletubbies.entity.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.debug.teletubbies.entity.ProductCover;
import com.debug.teletubbies.mapper.ProductMapper;
import com.debug.teletubbies.service.ProductService;
import com.debug.teletubbies.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service("flowerService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAllFlowers() {

        List<Product> products = productMapper.findAllFlowers();
        return products;

    }

    @Override
    public Product inserFlower(Product product) {

        Integer rowCount = productMapper.insertSelective(product);
        return product;

    }

    @Override
    public Integer modifyFlower(Product product) {
        Integer rowCount = productMapper.updateByPrimaryKeySelective(product);
        return  rowCount;
    }

    @Override
    public Integer deleteFlower(Integer flowerId) {

        Integer rowCount = productMapper.deleteByPrimaryKey(flowerId);
        return  rowCount;
    }

    @Override
    public Product findFlower(Integer flowerId) {

        Product product = productMapper.selectByPrimaryKey(flowerId);
        return product;
    }


    @Override
    public PageBean queryFlowers(Integer currentPage, Integer pageSize, Product condition) {

        Page page = PageHelper.startPage(currentPage,pageSize);
        List<Product> products = productMapper.queryFlowers(condition);
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, products);
        return pageBean;
    }

    @Override
    public PageBean findFlowersByKey(Integer currentPage, Integer pageSize, String key) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        List<Product> products = productMapper.findFlowersByKey(key);
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, products);
        return pageBean;
    }

    @Override
    public PageBean queryFlowerCover(Integer currentPage, Integer pageSize, Product condition) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        List<ProductCover> productCovers = productMapper.queryFlowerCovers(condition);
        for(ProductCover productCover : productCovers) {
            String flowerImgPath = productMapper.getFlowerCover(productCover.getFlowerId());
            productCover.setFlowerImgPath(flowerImgPath);
        }
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, productCovers);
        return pageBean;
    }

    @Override
    public Map<String,Object> getFlowerDetail(Integer flowerId) {
        Product product =  this.findFlower(flowerId);
        List<String> imgUrls = productMapper.getFlowerImgs(flowerId);
        Map<String,Object> res = new LinkedHashMap<>();
        res.put("flower", product);
        res.put("imgUrls",imgUrls);
        return res;
    }

    @Override
    public PageBean queryFlowerCoverByKey(Integer currentPage, Integer pageSize, String key) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        List<ProductCover> productCovers = productMapper.queryFlowerCoversByKey(key);
        for(ProductCover productCover : productCovers) {
            String flowerImgPath = productMapper.getFlowerCover(productCover.getFlowerId());
            productCover.setFlowerImgPath(flowerImgPath);
        }
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, productCovers);
        return pageBean;
    }


}

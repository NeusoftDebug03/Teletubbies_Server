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


@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAllProducts() {

        List<Product> products = productMapper.findAllProducts();
        return products;

    }

    @Override
    public Product inserProduct(Product product) {

        Integer rowCount = productMapper.insertSelective(product);
        return product;

    }

    @Override
    public Integer modifyProduct(Product product) {
        Integer rowCount = productMapper.updateByPrimaryKeySelective(product);
        return  rowCount;
    }

    @Override
    public Integer deleteProduct(Integer productId) {

        Integer rowCount = productMapper.deleteByPrimaryKey(productId);
        return  rowCount;
    }

    @Override
    public Product findProduct(Integer productId) {

        Product product = productMapper.selectByPrimaryKey(productId);
        return product;
    }


    @Override
    public PageBean queryProducts(Integer currentPage, Integer pageSize, Product condition) {

        Page page = PageHelper.startPage(currentPage,pageSize);
        List<Product> products = productMapper.queryProducts(condition);
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, products);
        return pageBean;
    }

    @Override
    public PageBean findProductsByKey(Integer currentPage, Integer pageSize, String key) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        List<Product> products = productMapper.findProductsByKey(key);
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, products);
        return pageBean;
    }

    @Override
    public PageBean queryProductCover(Integer currentPage, Integer pageSize, Product condition) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        List<ProductCover> productCovers = productMapper.queryProductCovers(condition);
        for(ProductCover productCover : productCovers) {
            String productImgPath = productMapper.getProductCover(productCover.getProductId());
            productCover.setProductImgPath(productImgPath);
        }
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, productCovers);
        return pageBean;
    }

    @Override
    public Map<String,Object> getProductDetail(Integer productId) {
        Product product =  this.findProduct(productId);
        List<String> imgUrls = productMapper.getProductImgs(productId);
        Map<String,Object> res = new LinkedHashMap<>();
        res.put("product", product);
        res.put("imgUrls",imgUrls);
        return res;
    }

    @Override
    public PageBean queryProductCoverByKey(Integer currentPage, Integer pageSize, String key) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        List<ProductCover> productCovers = productMapper.queryProductCoversByKey(key);
        for(ProductCover productCover : productCovers) {
            String productImgPath = productMapper.getProductCover(productCover.getProductId());
            productCover.setProductImgPath(productImgPath);
        }
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage, productCovers);
        return pageBean;
    }


}

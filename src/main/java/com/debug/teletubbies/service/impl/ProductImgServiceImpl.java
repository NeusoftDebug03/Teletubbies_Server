package com.debug.teletubbies.service.impl;

import com.debug.teletubbies.entity.ProductImg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.debug.teletubbies.entity.Product;
import com.debug.teletubbies.mapper.ProductImgMapper;
import com.debug.teletubbies.mapper.ProductMapper;
import com.debug.teletubbies.service.ProductImgService;
import com.debug.teletubbies.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("productImgService")
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    private ProductImgMapper productImgMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductImg> getProductImgsById(Integer productId) {

        List<ProductImg> productImgs = productImgMapper.getProductImgsById(productId);
        return productImgs;

    }

    @Override
    public void addProductImg(Integer productId, String imgPath) {
        ProductImg productImg = new ProductImg();
        productImg.setProductId(productId);
        productImg.setProductImgPath(imgPath);
        System.out.println(productImg.getProductId());
        System.out.println(productImg.getProductImgPath());
//        productImgMapper.insert(productImg);
        productImgMapper.update(productImg);
    }

    @Override
    public void delProductImg(ProductImg productImg) {
        productImgMapper.delProductImg(productImg);
    }

    @Override
    public PageBean queryProductsImgs(Integer currentPage, Integer pageSize) {

        Page page = PageHelper.startPage(currentPage,pageSize,true);
        List<Product> allProducts = productMapper.findAllProducts();
        if (allProducts == null || allProducts.isEmpty()) {
            return new PageBean(currentPage,pageSize,0,false,0,null);
        }
        List<Map<String,Object>> productsImgs = new ArrayList<>();
        for(Product product : allProducts) {
            Map<String, Object> productImgs = new LinkedHashMap<>();
            productImgs.put("productId", product.getProductId());
            List<ProductImg> productImg = productImgMapper.getProductImgsById(product.getProductId());
            productImgs.put("productImgs", productImg);
            productsImgs.add(productImgs);
        }
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage,productsImgs);
        return pageBean;
    }
}

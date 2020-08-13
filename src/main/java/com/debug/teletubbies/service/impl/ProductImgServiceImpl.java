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

@Service("flowerImgService")
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    private ProductImgMapper productImgMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductImg> getFlowerImgsById(Integer flowerId) {

        List<ProductImg> productImgs = productImgMapper.getFlowerImgsById(flowerId);
        return productImgs;

    }

    @Override
    public void addFlowerImg(Integer flowerId, String imgPath) {
        ProductImg productImg = new ProductImg();
        productImg.setFlowerId(flowerId);
        productImg.setFlowerImgPath(imgPath);
        System.out.println(productImg.getFlowerId());
        System.out.println(productImg.getFlowerImgPath());
        productImgMapper.insert(productImg);
    }

    @Override
    public void delFlowerImg(ProductImg productImg) {
        productImgMapper.delFlowerImg(productImg);
    }

    @Override
    public PageBean queryFlowersImgs(Integer currentPage, Integer pageSize) {

        Page page = PageHelper.startPage(currentPage,pageSize,true);
        List<Product> allProducts = productMapper.findAllFlowers();
        if (allProducts == null || allProducts.isEmpty()) {
            return new PageBean(currentPage,pageSize,0,false,0,null);
        }
        List<Map<String,Object>> flowersImgs = new ArrayList<>();
        for(Product product : allProducts) {
            Map<String, Object> flowerImgs = new LinkedHashMap<>();
            flowerImgs.put("flowerId", product.getFlowerId());
            List<ProductImg> productImg = productImgMapper.getFlowerImgsById(product.getFlowerId());
            flowerImgs.put("flowerImgs", productImg);
            flowersImgs.add(flowerImgs);
        }
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage,flowersImgs);
        return pageBean;
    }
}

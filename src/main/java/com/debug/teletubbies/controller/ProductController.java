package com.debug.teletubbies.controller;

import com.alibaba.fastjson.JSONObject;
import com.debug.teletubbies.entity.Product;
import com.debug.teletubbies.service.ProductService;
import com.debug.teletubbies.tools.PageBean;
import com.debug.teletubbies.tools.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "ProductController", description = "商品管理")
@RestController
@RequestMapping(value = "api")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    * 列出所有商品
    * */
    @ApiOperation("列出所有商品")
    @RequestMapping(value = "list_all_products", method = RequestMethod.POST)
    public ResponseDto listAllProducts(){

        List<Product> products = productService.findAllProducts();

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("列出所有商品");
        dto.setData(products);
        return dto;
    }

    /*
    *  添加商品
    * 参数{
    * Product
    * }
    * */
    @ApiOperation("添加商品")
    @RequestMapping(value = "add_product", method = RequestMethod.POST)
    public ResponseDto addProduct(@RequestBody Product product) {

        Product insert_product = productService.inserProduct(product);

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("添加商品成功");
        dto.setData(insert_product);
        return dto;
    }

    /*
    * 修改商品
    * 参数{
    * Product
    * }
    * */
    @ApiOperation("修改商品")
    @RequestMapping(value = "modify_product", method = RequestMethod.POST)
    public ResponseDto modifyProduct(@RequestBody Product product) {

        Integer rowCount = productService.modifyProduct(product);

        ResponseDto dto = new ResponseDto();
        dto.setCode(rowCount.toString());
        dto.setMsg("修改商品成功");
        return dto;
    }

    /*
    * 删除商品
    * 参数{
    * productId
    * }
    * */
    @ApiOperation("删除商品")
    @RequestMapping(value = "del_product", method = RequestMethod.POST)
    public ResponseDto deleteProduct(@RequestBody JSONObject jsonObject) {

        Integer productId = jsonObject.getInteger("productId");
        Integer rowCount = productService.deleteProduct(productId);

        ResponseDto dto = new ResponseDto();
        dto.setCode(rowCount.toString());
        dto.setMsg("删除商品成功");
        return dto;
    }

    /*
    * 通过id查找商品
    * 参数{
    * productId
    * }
    * */
    @ApiOperation("通过ID查找商品")
    @RequestMapping(value = "find_product", method = RequestMethod.POST)
    public ResponseDto findProduct(@RequestBody JSONObject jsonObject) {

        Integer productId = jsonObject.getInteger("productId");

        Product product = productService.findProduct(productId);

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("通过id查询商品");
        dto.setData(product);
        return dto;
    }

    /*
     * 通过关键词查找商品
     * 参数{
     * key
     * }
     * */
    @ApiOperation("通过关键词查找商品")
    @RequestMapping(value = "find_key_products", method = RequestMethod.POST)
    public ResponseDto findProductsByKey(@RequestBody JSONObject jsonObject) {

        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        String key = jsonObject.getString("key");

        PageBean products = productService.findProductsByKey(currentPage,pageSize,key);
        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("通过key查询商品");
        dto.setData(products);
        return dto;
    }


    /*
    * 查询商品
    * 参数{
    * currentPage：
    * pageSize：
    * condition:{
    *
    * }
    * */
    @ApiOperation("查询商品")
    @RequestMapping(value = "query_products", method = RequestMethod.POST)
    public ResponseDto queryProducts(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        JSONObject productJSON = jsonObject.getJSONObject("condition");
        Product condition = JSONObject.toJavaObject(productJSON, Product.class);

        PageBean products = productService.queryProducts(currentPage,pageSize,condition);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询商品");
        res.setData(products);
        return res;
    }

    /*
    * 获取商品封面
    * */
    @ApiOperation("获取商品封面")
    @RequestMapping(value = "query_product_cover", method = RequestMethod.POST)
    public ResponseDto queryProductCover(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        JSONObject productJSON = jsonObject.getJSONObject("condition");
        Product condition = JSONObject.toJavaObject(productJSON, Product.class);

        PageBean productCovers  = productService.queryProductCover(currentPage,pageSize,condition);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询商品封面");
        res.setData(productCovers);
        return res;
    }

    /*
    * 获取商品详情（带图片地址）
    * */
    @ApiOperation("获取商品详情（带图片地址）")
    @RequestMapping(value = "get_product_detail", method = RequestMethod.POST)
    public ResponseDto getProductDetail(@RequestBody JSONObject jsonObject) {
        Integer productId = jsonObject.getInteger("productId");
        Map<String,Object> productWithImgPath = productService.getProductDetail(productId);
        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询商品详情");
        res.setData(productWithImgPath);
        return res;
    }

    /*
    *通过关键词获取商品封面
    * */
    @ApiOperation("通过关键词获取商品封面")
    @RequestMapping(value = "get_key_product_cover", method = RequestMethod.POST)
    public ResponseDto getProductCoverByKey(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        String key = jsonObject.getString("key");

        PageBean productCovers  = productService.queryProductCoverByKey(currentPage,pageSize,key);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("通过关键词查询商品封面");
        res.setData(productCovers);
        return res;
    }



}

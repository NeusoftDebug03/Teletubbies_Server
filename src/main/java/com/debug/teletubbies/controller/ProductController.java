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
    @RequestMapping(value = "list_all_flowers", method = RequestMethod.POST)
    public ResponseDto listAllFlowers(){

        List<Product> products = productService.findAllFlowers();

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("列出所有商品");
        dto.setData(products);
        return dto;
    }

    /*
    *  添加商品
    * 参数{
    * Flower
    * }
    * */
    @ApiOperation("添加商品")
    @RequestMapping(value = "add_flower", method = RequestMethod.POST)
    public ResponseDto addFlower(@RequestBody Product product) {

        Product insert_product = productService.inserFlower(product);

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("添加商品成功");
        dto.setData(insert_product);
        return dto;
    }

    /*
    * 修改商品
    * 参数{
    * Flower
    * }
    * */
    @ApiOperation("修改商品")
    @RequestMapping(value = "modify_flower", method = RequestMethod.POST)
    public ResponseDto modifyFlower(@RequestBody Product product) {

        Integer rowCount = productService.modifyFlower(product);

        ResponseDto dto = new ResponseDto();
        dto.setCode(rowCount.toString());
        dto.setMsg("修改商品成功");
        return dto;
    }

    /*
    * 删除商品
    * 参数{
    * flowerId
    * }
    * */
    @ApiOperation("删除商品")
    @RequestMapping(value = "del_flower", method = RequestMethod.POST)
    public ResponseDto deleteFlower(@RequestBody JSONObject jsonObject) {

        Integer flowerId = jsonObject.getInteger("flowerId");
        Integer rowCount = productService.deleteFlower(flowerId);

        ResponseDto dto = new ResponseDto();
        dto.setCode(rowCount.toString());
        dto.setMsg("删除商品成功");
        return dto;
    }

    /*
    * 通过id查找商品
    * 参数{
    * flowerId
    * }
    * */
    @ApiOperation("通过ID查找商品")
    @RequestMapping(value = "find_flower", method = RequestMethod.POST)
    public ResponseDto findFlower(@RequestBody JSONObject jsonObject) {

        Integer flowerId = jsonObject.getInteger("flowerId");

        Product product = productService.findFlower(flowerId);

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
    @RequestMapping(value = "find_key_flowers", method = RequestMethod.POST)
    public ResponseDto findFlowersByKey(@RequestBody JSONObject jsonObject) {

        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        String key = jsonObject.getString("key");

        PageBean flowers = productService.findFlowersByKey(currentPage,pageSize,key);
        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("通过key查询商品");
        dto.setData(flowers);
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
    @RequestMapping(value = "query_flowers", method = RequestMethod.POST)
    public ResponseDto queryFlowers(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        JSONObject flowerJSON = jsonObject.getJSONObject("condition");
        Product condition = JSONObject.toJavaObject(flowerJSON, Product.class);

        PageBean flowers = productService.queryFlowers(currentPage,pageSize,condition);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询商品");
        res.setData(flowers);
        return res;
    }

    /*
    * 获取商品封面
    * */
    @ApiOperation("获取商品封面")
    @RequestMapping(value = "query_flower_cover", method = RequestMethod.POST)
    public ResponseDto queryFlowerCover(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        JSONObject flowerJSON = jsonObject.getJSONObject("condition");
        Product condition = JSONObject.toJavaObject(flowerJSON, Product.class);

        PageBean flowerCovers  = productService.queryFlowerCover(currentPage,pageSize,condition);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询商品封面");
        res.setData(flowerCovers);
        return res;
    }

    /*
    * 获取商品详情（带图片地址）
    * */
    @ApiOperation("获取商品详情（带图片地址）")
    @RequestMapping(value = "get_flower_detail", method = RequestMethod.POST)
    public ResponseDto getFlowerDetail(@RequestBody JSONObject jsonObject) {
        Integer flowerId = jsonObject.getInteger("flowerId");
        Map<String,Object> flowerWithImgPath = productService.getFlowerDetail(flowerId);
        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询商品详情");
        res.setData(flowerWithImgPath);
        return res;
    }

    /*
    *通过关键词获取商品封面
    * */
    @ApiOperation("通过关键词获取商品封面")
    @RequestMapping(value = "get_key_flower_cover", method = RequestMethod.POST)
    public ResponseDto getFlowerCoverByKey(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        String key = jsonObject.getString("key");

        PageBean flowerCovers  = productService.queryFlowerCoverByKey(currentPage,pageSize,key);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("通过关键词查询商品封面");
        res.setData(flowerCovers);
        return res;
    }



}

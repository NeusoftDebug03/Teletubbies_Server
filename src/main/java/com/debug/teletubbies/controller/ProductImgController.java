package com.debug.teletubbies.controller;

import com.alibaba.fastjson.JSONObject;
import com.debug.teletubbies.entity.ProductImg;
import com.debug.teletubbies.service.ProductImgService;
import com.debug.teletubbies.tools.Base64MultipartFile;
import com.debug.teletubbies.tools.PageBean;
import com.debug.teletubbies.tools.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Api(tags = "ProductController", description = "商品图片管理")
@RestController
@RequestMapping(value = "api")
public class ProductImgController {

    @Autowired
    private ProductImgService productImgService;

    @Value("${myport}")
    private String port;

    private String host;

//    private String rootPath = "E:";
    private String rootPath = "D:";

    private String sonPath = "/imgs/";

    private String imgPath;

    /*
    * 通过商品id获取图片
    * 参数 {
    * flowerId
    * }
    * */
    @ApiOperation("通过商品ID获取图片")
    @RequestMapping(value = "get_flower_imgs", method = RequestMethod.POST)
    public ResponseDto getFlowerImgs(@RequestBody JSONObject jsonObject) {

        Integer flowerId = jsonObject.getInteger("flowerId");

        List<ProductImg> productImgs = productImgService.getFlowerImgsById(flowerId);

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("通过商品ID查询图片");
        dto.setData(productImgs);
        return  dto;
    }

    @ApiOperation("更新商品图片")
    @RequestMapping(value = "/upload_flower_img", method = RequestMethod.POST)
    public ResponseDto uploadFlowerImg(@RequestParam("img")MultipartFile file,@RequestParam Integer flowerId) {
        ResponseDto res = new ResponseDto();
        if(file.isEmpty()) {
            res.setCode("0");
            res.setMsg("文件为空");
            return res;
        }

        host = "127.0.0.1";

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName;
        String filePath = rootPath + sonPath;

        File dest = new File(filePath + fileName);
        String imgPath = ("http://"+host + ":" + port + sonPath + fileName).toString();

        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }

        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            //将链接保存到URL中
            productImgService.addFlowerImg(flowerId,imgPath);
            res.setCode("1");
            res.setMsg("上传成功");
            return res;
        } catch (Exception e) {
            res.setCode("0");
            res.setMsg("上传失败");
            return res;
        }
    }

    @ApiOperation("更新商品图片base")
    @RequestMapping(value = "upload_flower_img_base", method = RequestMethod.POST)
    public ResponseDto uploadBase64FlowerImg(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest) {
        Integer flowerId = jsonObject.getInteger("flowerId");
        String imgString = jsonObject.getString("img");
        String[] baseStrs = imgString.split(",");
        String fileName = UUID.randomUUID() + "." + Base64MultipartFile.checkImageBase64Format(baseStrs[1]);
        System.out.println(fileName);
        MultipartFile file = Base64MultipartFile.base64toMultipart(imgString, fileName);

        host = "127.0.0.1";
        String port1 = "8383";

        String filePath = rootPath + sonPath;
        File dest = new File(filePath + fileName);
        String imgPath = ("http://"+host + ":" + port1 + sonPath + fileName).toString();

        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }

        ResponseDto res = new ResponseDto();
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            //将链接保存到URL中
            productImgService.addFlowerImg(flowerId,imgPath);
            res.setCode("1");
            res.setMsg("上传成功");
            res.setData(imgPath);
            return res;
        } catch (Exception e) {
            res.setCode("0");
            res.setMsg("上传失败");
            return res;
        }
    }

    /*
    * 删除鲜花图片
    * 参数{
    * flowerId
    * flowerImgPath
    * }
    * */
    @ApiOperation("删除商品图片")
    @RequestMapping(value = "del_flower_img", method = RequestMethod.POST)
    public ResponseDto delFlowerImg(@RequestBody ProductImg productImg) {
        productImgService.delFlowerImg(productImg);
        ResponseDto res = new ResponseDto();

        res.setCode("1");
        res.setMsg("删除商品图片");
        return res;
    }

    /*
    * 获取所有商品的图片
    * */
    @ApiOperation("获取所有商品图片")
    @RequestMapping(value = "query_flowers_imgs", method = RequestMethod.POST)
    public ResponseDto queryFlowersImgs(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        PageBean flowersImgs = productImgService.queryFlowersImgs(currentPage,pageSize);
        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询所有商品的图片");
        res.setData(flowersImgs);
        return res;
    }


    
}

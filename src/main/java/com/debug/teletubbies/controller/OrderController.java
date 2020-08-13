package com.debug.teletubbies.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.debug.teletubbies.entity.*;
import com.debug.teletubbies.service.*;
import com.debug.teletubbies.tools.PageBean;
import com.debug.teletubbies.tools.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@Api(tags = "OrderController", description = "订单管理")
@RestController
@RequestMapping(value = "api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ProductService productService;

    /**
     * 列出全部订单
     * @return
     */
    @ApiOperation("列出全部订单")
    @RequestMapping(value = "list_all_orders", method = RequestMethod.POST)
    public ResponseDto listAllOrders() {

        List<Order> orders = orderService.listAllOrders();

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("列出所有订单简要信息");
        res.setData(orders);
        return res;
    }
    /*
    * 通过订单id获取订单详细信息
    *参数{
    * orderId:
    * }
    * */
    @ApiOperation("通过订单ID获取订单详细信息")
    @RequestMapping(value = "get_order_detail", method = RequestMethod.POST)
    public ResponseDto getOrderDetail(@RequestBody JSONObject jsonObject) {
        Integer orderId = jsonObject.getInteger("orderId");

        Order order = orderService.getOrderById(orderId);
        User user = userService.getUserById(order.getUserId());
        user.setPasswd("******");
        List<Content> contents = contentService.getContentsById(order.getContentId());
        Status status = statusService.getStatusById(order.getStatusId());

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("通过订单号查询订单详细信息");
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("order",order);
        data.put("user",user);
        data.put("contents",contents);
        data.put("status",status);
        res.setData(data);
        return res;
    }

    /*
    * 创建新的订单
    * 参数{
    * userId:
    * content:[
    *   {
    *       productId:
    *       count:
    *   },
    * ]
    * }
    *
    * */
    @ApiOperation("创建新的订单")
    @RequestMapping(value = "create_order", method = RequestMethod.POST)
    public ResponseDto createNewOrder(@RequestBody JSONObject jsonObject) {
        /*参数获取*/
        Integer userId = jsonObject.getInteger("userId");
        JSONArray contents = jsonObject.getJSONArray("contents");

        /*
        * 生成contentId
        * */
        Integer contentId = UUID.randomUUID().toString().hashCode();
        contentId = contentId< 0 ? -contentId : contentId;

        /*
        * Calculate out the cost
        * */
        BigDecimal cost = new BigDecimal("0");
        ResponseDto res = new ResponseDto();
        if (contents.size() > 0) {
            for(int i=0; i<contents.size(); ++i) {
                JSONObject content = contents.getJSONObject(i);

                Integer productId = content.getInteger("productId");
                Integer count = content.getInteger("count");
                /*
                * Get price and discount
                * */
                Product product = productService.findProduct(productId);

                System.out.println(count);
                System.out.println(product.getProductStock());
                // 库存不足
                if (product.getProductStock() < count) {
                    res.setCode("0");
                    res.setMsg("鲜花" + product.getProductId() + ":\"" + product.getProductName() + "\"库存不足");
                    return res;
                }
                // 修改库存
                Product updateProduct = new Product();
                updateProduct.setProductId(product.getProductId());
                updateProduct.setProductStock(product.getProductStock() - count);
                // 更新鲜花
                productService.modifyProduct(updateProduct);

                // 计算价格
                BigDecimal productPrice = product.getProductPrice();
                BigDecimal productDiscount = product.getProductDiscount();
                BigDecimal price = productPrice.multiply(productDiscount);

                /*
                * cost = cost + price*count
                * */
                cost = cost.add(price.multiply(BigDecimal.valueOf(count)));

                /*
                * 修改账户余额
                * */
                User user = userService.getUserById(userId);
                if (user.getBalance().subtract(cost).doubleValue() < 0 ) {
                    res.setCode("0");
                    res.setMsg("余额不足");
                    return res;
                }
                user.setBalance(user.getBalance().subtract(cost));
                userService.modifyUser(user);

                /*
                * insert to db
                * */
                Content iContent = new Content(contentId,productId,count);
                Integer rowCount = contentService.InsertContent(iContent);

            }
            /*
             * init status is 1(the order has been placed)
             * */
            Integer statusId = 1;

            Order order = new Order(userId,contentId,cost,statusId);

            Order orderWithId = orderService.insertOrder(order);

            res.setCode("1");
            res.setMsg("生成订单成功");
            res.setData(orderWithId);
        } else {
            res.setCode("0");
            res.setMsg("订单中没有商品");
        }

        return  res;
    }

    /*
    * 修改订单状态
    * 参数:{
    * orderId:
    * statusId:
    * }
    *
    * */
    @ApiOperation("修改订单状态")
    @RequestMapping(value = "modify_order_status", method = RequestMethod.POST)
    public ResponseDto modifyOrderStatus(@RequestBody JSONObject jsonObject) {
        Integer orderId = jsonObject.getInteger("orderId");
        Integer statusId = jsonObject.getInteger("statusId");

        Integer rowCount = orderService.modifyOrderStatus(orderId,statusId);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("修改订单状态");

        return  res;
    }

    /*
    * 删除订单
    * 参数：{
    * orderId
    * }
    * */
    @ApiOperation("删除订单")
    @RequestMapping(value = "del_order", method = RequestMethod.POST)
    public ResponseDto deleteOrder(@RequestBody JSONObject jsonObject) {
        Integer orderId = jsonObject.getInteger("orderId");


        // 如果订单没有被确认收货就恢复库存
        Order order = orderService.getOrderById(orderId);
        List<Content> contents = contentService.getContentsById(order.getContentId());
        
        for(Content content:contents) {
            Integer productId = content.getProductId();
            Integer count = content.getCount();
            Product product = productService.findProduct(productId);

            Product updateProduct = new Product();
            updateProduct.setProductId(productId);
            updateProduct.setProductStock(product.getProductStock() + count);
            productService.modifyProduct(updateProduct);
        }
        contentService.deleteContent(order.getContentId());
        orderService.deleteOrder(orderId);




        ResponseDto res = new ResponseDto();

        res.setCode("1");
        res.setMsg("通过Id删除订单");

        return res;
    }

    /*查询订单
    * 参数{
    * currentPage：
    * pageSize：
    * condition:{
    * }
    * */
    @ApiOperation("查询订单")
    @RequestMapping(value = "queryOrders", method = RequestMethod.POST)
    public ResponseDto queryOrders(@RequestBody JSONObject jsonObject) {
        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        JSONObject productJSON = jsonObject.getJSONObject("condition");
        Order condition = JSONObject.toJavaObject(productJSON,Order.class);

        PageBean orders = orderService.queryOrders(currentPage,pageSize,condition);
        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询订单");
        res.setData(orders);
        return res;
    }

    /*
    * 获取销售情况
    * */
    @ApiOperation("获取销售情况")
    @RequestMapping(value = "sale_info", method = RequestMethod.POST)
    public ResponseDto getSaleInfo() {
        List<Product> allProducts = productService.findAllProducts();
        List<Map<String,Object>> res = new ArrayList<>();
        for (Product product : allProducts) {
            Integer productId = product.getProductId();
            String productName = product.getProductName();
            Integer count = orderService.getProductSaleCount(productId);
            count = count == null?0:count;
            Map<String,Object> productCount = new LinkedHashMap<>();
            productCount.put("productId",productId);
            productCount.put("productName",productName);
            productCount.put("count",count);
            res.add(productCount);
        }
        ResponseDto result = new ResponseDto();
        result.setCode("1");
        result.setMsg("查询销量信息");
        result.setData(res);

        return result;
    }

}

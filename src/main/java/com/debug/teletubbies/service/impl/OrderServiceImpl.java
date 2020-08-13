package com.debug.teletubbies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.debug.teletubbies.entity.Order;
import com.debug.teletubbies.mapper.OrderMapper;
import com.debug.teletubbies.service.OrderService;
import com.debug.teletubbies.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> listAllOrders() {

        List<Order> orders = orderMapper.listAllOrders();
        return  orders;

    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        return order;
    }

    @Override
    public Order insertOrder(Order order) {

        int rowCount = orderMapper.insertSelective(order);
        return order;

    }

    @Override
    public Integer modifyOrderStatus(Integer orderId, Integer statusId) {

        Integer rowCount = orderMapper.modifyOrderStatus(orderId,statusId);
        return rowCount;

    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public PageBean queryOrders(Integer currentPage, Integer pageSize, Order condition) {

        Page page = PageHelper.startPage(currentPage,pageSize);
        List<Order> orders = orderMapper.queryProducts(condition);
        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;
        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage,orders);
        return pageBean;
    }

    @Override
    public Integer getProductSaleCount(Integer productId) {
        return orderMapper.getProductSaleCount(productId);
    }
}

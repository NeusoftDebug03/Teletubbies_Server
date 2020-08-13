package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.Order;
import com.debug.teletubbies.tools.PageBean;

import java.util.List;

public interface OrderService {
    List<Order> listAllOrders();

    Order getOrderById(Integer orderId);

    Order insertOrder(Order order);

    Integer modifyOrderStatus(Integer orderId, Integer statusId);

    void deleteOrder(Integer orderId);

    PageBean queryOrders(Integer currentPage, Integer pageSize, Order condition);

    Integer getFlowerSaleCount(Integer flowerId);
}

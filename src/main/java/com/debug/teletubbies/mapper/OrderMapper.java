package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> listAllOrders();

    Integer modifyOrderStatus(@Param("orderId") Integer orderId, @Param("statusId") Integer statusId);

    List<Order> queryFlowers(Order condition);

    Integer getFlowerSaleCount(Integer flowerId);
}
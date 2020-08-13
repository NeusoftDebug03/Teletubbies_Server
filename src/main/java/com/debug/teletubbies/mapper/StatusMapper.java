package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.Status;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatusMapper {
    int deleteByPrimaryKey(Integer statusId);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(Integer statusId);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);
}
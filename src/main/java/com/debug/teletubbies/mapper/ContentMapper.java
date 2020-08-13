package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    int insert(Content record);

    int insertSelective(Content record);

    List<Content> getContentsById(Integer contentId);

    void deleteContent(Integer contentId);
}
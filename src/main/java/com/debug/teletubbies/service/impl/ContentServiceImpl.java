package com.debug.teletubbies.service.impl;

import com.debug.teletubbies.entity.Content;
import com.debug.teletubbies.mapper.ContentMapper;
import com.debug.teletubbies.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<Content> getContentsById(Integer contentId) {

        List<Content> contents = contentMapper.getContentsById(contentId);
        return contents;

    }

    @Override
    public Integer InsertContent(Content content) {

        Integer rowCount = contentMapper.insertSelective(content);
        return rowCount;

    }

    @Override
    public void deleteContent(Integer contentId) {
        contentMapper.deleteContent(contentId);
    }
}

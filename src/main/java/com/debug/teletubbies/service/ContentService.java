package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.Content;

import java.util.List;

public interface ContentService {
    List<Content> getContentsById(Integer contentId);

    Integer InsertContent(Content content);

    void deleteContent(Integer contentId);
}

package com.debug.teletubbies.service.impl;

import com.debug.teletubbies.entity.Status;
import com.debug.teletubbies.mapper.StatusMapper;
import com.debug.teletubbies.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public Status getStatusById(Integer statusId) {

        Status status = statusMapper.selectByPrimaryKey(statusId);
        return  status;

    }
}

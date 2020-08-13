package com.debug.teletubbies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.debug.teletubbies.entity.User;
import com.debug.teletubbies.mapper.UserMapper;
import com.debug.teletubbies.service.UserService;
import com.debug.teletubbies.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsernameAndPasswd(String username, String passwd) {
        User user = userMapper.findUserByUsernameAndPasswd(username,passwd);
        return user;

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userMapper.listAllUsers();
        for(User user : users) {
            user.setPasswd("******");
        }
        return users;
    }

    @Override
    public User addUser(User user) {

        int insert = userMapper.insertSelective(user);
        return user;
    }

    @Override
    public Integer deleteUser(Integer userId) {

        Integer rowCount = userMapper.deleteByPrimaryKey(userId);
        return rowCount;
    }

    @Override
    public Integer modifyUser(User user) {

        int i = userMapper.updateByPrimaryKey(user);
        return i;
    }

    @Override
    public User getUserByUsername(String username) {

        User user = userMapper.getUserByUsername(username);
        return user;
    }

    @Override
    public User getUserById(Integer userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        return user;

    }

    @Override
    public PageBean conditionalQueryUsers(Integer currentPage, Integer pageSize, User condition) {

        Page page = PageHelper.startPage(currentPage,pageSize,true);
        List<User> users = userMapper.conditionalQueryUsers(condition);

        Long totalNum = page.getTotal();
        Integer totalPage = page.getPages();
        Boolean isMore = currentPage<totalPage?true:false;

        PageBean pageBean = new PageBean(currentPage,pageSize,totalNum,isMore,totalPage,users);
        return pageBean;
    }

    @Override
    public Integer addBalance(Integer userId, Integer balance) {
        User user = this.getUserById(userId);
        user.setBalance(user.getBalance().add(BigDecimal.valueOf(balance)) );
        Integer rowCount = this.modifyUser(user);
        return rowCount;
    }
}

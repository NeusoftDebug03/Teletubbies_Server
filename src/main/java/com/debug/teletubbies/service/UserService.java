package com.debug.teletubbies.service;

import com.debug.teletubbies.entity.User;
import com.debug.teletubbies.tools.PageBean;

import java.util.List;

public interface UserService {
    /*
    * 通过用户名和密码查询用户
    * */
    public User findUserByUsernameAndPasswd(String username, String passwd);

    List<User> getAllUsers();

    User addUser(User user);

    Integer deleteUser(Integer userId);

    Integer modifyUser(User user);

    User getUserByUsername(String username);

    User getUserById(Integer userId);

    PageBean conditionalQueryUsers(Integer currentPage, Integer pageSize, User condition);

    Integer addBalance(Integer userId, Integer balance);
}

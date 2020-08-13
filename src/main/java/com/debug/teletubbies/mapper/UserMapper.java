package com.debug.teletubbies.mapper;

import com.debug.teletubbies.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByUsernameAndPasswd(@Param("username") String username, @Param("passwd") String passwd);

    List<User> listAllUsers();

    Integer deleteByUsername(String username);

    User getUserByUsername(String username);

    List<User> conditionalQueryUsers(User condition);
}
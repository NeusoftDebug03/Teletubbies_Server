package com.debug.teletubbies.controller;

import com.alibaba.fastjson.JSONObject;
import com.debug.teletubbies.entity.User;
import com.debug.teletubbies.service.UserService;
import com.debug.teletubbies.tools.PageBean;
import com.debug.teletubbies.tools.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping(value = "api")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    * 列出所有用户
    * */
    @ApiOperation("列出所有用户")
    @RequestMapping(value = "list_all_users", method = RequestMethod.POST)
    public ResponseDto listAllUsers() {
        List<User> users =  userService.getAllUsers();
        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("查询所有用户");
        dto.setData(users);
        return  dto;
    }

    /*
    * 添加用户
    * */
    @ApiOperation("添加用户")
    @RequestMapping(value = "add_user", method = RequestMethod.POST)
    public ResponseDto addUser(@RequestBody User user) {
        ResponseDto dto = new ResponseDto();
        if(user.getPhoneNumber() == null) {
            dto.setCode("-1");
            dto.setMsg("手机号未填");
            return dto;
        }
        String username = user.getUsername();
        User findUser = userService.getUserByUsername(username);

        if(findUser != null) {
            dto.setCode("0");
            dto.setMsg("用户名已存在");
        } else {
            User insertUser =  userService.addUser(user);
            dto.setCode("1");
            dto.setMsg("添加用户成功");
            dto.setData(insertUser);
        }
        return  dto;
    }


    /*
    * 修改用户
    * */
    @ApiOperation("修改用户")
    @RequestMapping(value = "modify_user", method = RequestMethod.POST)
    public ResponseDto modifyUser(@RequestBody User user) {

        Integer rowCount =  userService.modifyUser(user);

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("修改用户成功");
        return  dto;
    }

    /*
    * 删除用户
    * 参数{
    * username:
    * }
    * */
    @ApiOperation("删除用户")
    @RequestMapping(value = "del_user", method = RequestMethod.POST)
    public ResponseDto deleteUser(@RequestBody JSONObject jsonObject) {
        Integer userId = jsonObject.getInteger("userId");

        Integer rowCount =  userService.deleteUser(userId);

        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("删除用户成功");
        return  dto;
    }

    /*
    * 通过用户名查找用户
    *参数{
    * username:
    * }
    * */
    @ApiOperation("通过用户名查找用户")
    @RequestMapping(value = "find_user", method = RequestMethod.POST)
    public ResponseDto findUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");

        User user =  userService.getUserByUsername(username);
        user.setPasswd("******");
        ResponseDto dto = new ResponseDto();
        dto.setCode("1");
        dto.setMsg("通过用户名查询用户");
        dto.setData(user);
        return  dto;
    }

    /*
    * 条件查询用户
    * 参数{
    * currentPage：
    * pageSize：
    * condition:{
    *
    * }
    * }
    * */
    @ApiOperation("条件查询用户")
    @RequestMapping(value = "conditional_query_users", method = RequestMethod.POST)
    public ResponseDto conditionalQueryUsers(@RequestBody JSONObject jsonObject) {

        Integer currentPage = jsonObject.getInteger("currentPage");
        currentPage = currentPage==null?1:currentPage;
        Integer pageSize = jsonObject.getInteger("pageSize");
        pageSize = pageSize==null?10:pageSize;

        JSONObject userJSON = jsonObject.getJSONObject("condition");
        User condition = (User) JSONObject.toJavaObject(userJSON,User.class);

        PageBean users = userService.conditionalQueryUsers(currentPage,pageSize,condition);

        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("查询用户");
        res.setData(users);

        return res;
    }

    /*
    *   充值
    * */
    @ApiOperation("充值")
    @RequestMapping(value = "add_balance", method = RequestMethod.POST)
    public ResponseDto addBalance(@RequestBody JSONObject jsonObject) {
        Integer userId = jsonObject.getInteger("userId");
        Integer balance = jsonObject.getInteger("balance");
        Integer rowCount = userService.addBalance(userId,balance);
        ResponseDto res = new ResponseDto();
        res.setCode("1");
        res.setMsg("充值");
        return res;
    }


}

package com.debug.teletubbies.controller;

import com.alibaba.fastjson.JSONObject;
import com.debug.teletubbies.entity.User;
import com.debug.teletubbies.service.UserService;
import com.debug.teletubbies.tools.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "LoginController", description = "登录管理")
@RestController
@RequestMapping(value = "api")
public class LoginController {

    @Autowired
    private UserService userService;
    /*
    * 登录接口
    * 参数：{
    * username:
    * passwd:
    * }
    * 返回：UserRole
    * */
    @ApiOperation("登陆接口")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseDto login(@RequestBody JSONObject jsonObject) {
        // 获取参数
        String username = jsonObject.getString("username");
        String passwd = jsonObject.getString("passwd");

        // 根据密码查找用户
        User user = userService.findUserByUsernameAndPasswd(username, passwd);

        // 准备返回结果
        ResponseDto dto = new ResponseDto();
        if (user == null) {
            dto.setCode("0");
            dto.setMsg("账号不存在或密码错误");
            dto.setData(null);
        } else {
            // 隐藏用户密码
            user.setPasswd("******");

            dto.setCode("1");
            dto.setMsg("正确登录");
            dto.setData(user);
        }
        return dto;
    }

}

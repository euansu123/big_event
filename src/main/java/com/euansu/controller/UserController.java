package com.euansu.controller;

import com.euansu.pojo.Result;
import com.euansu.pojo.User;
import com.euansu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        // 查询用户名是否存在
        User u = userService.findByUserName(username);
        if(u != null) {
            // 被占用了
            return Result.error("用户名已被占用");
        } else{
            // 注册新用户
            userService.register(username,password);
            return Result.success();
        }
    }
}

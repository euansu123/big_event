package com.euansu.service;

import com.euansu.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);
    // 注册
    void register(String username, String password);
}

package com.euansu.service.impl;

import com.euansu.mapper.UserMapper;
import com.euansu.pojo.User;
import com.euansu.service.UserService;
import com.euansu.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // 密码做加密处理
        String md5String = Md5Util.getMD5String(password);
        // 用户注册
        userMapper.add(username, md5String);

    }
}

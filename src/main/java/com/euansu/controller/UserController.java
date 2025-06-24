package com.euansu.controller;

import com.euansu.pojo.Result;
import com.euansu.pojo.User;
import com.euansu.service.UserService;
import com.euansu.utils.Md5Util;
import com.euansu.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.euansu.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
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

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        // 根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        // 判断用户是否存在
        if(loginUser == null) {
            return Result.error("用户不存在");
        }

        // 判断密码是否正确
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            // 登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username", loginUser.getUsername());
            JwtUtil.generateToken(claims);
            return Result.success(JwtUtil.generateToken(claims));
        }else{
            return Result.error("登录密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> UserInfo(@RequestHeader(name="Authorization") String token){
        // 根据用户名查询用户
        // Map<String, Object> map = JwtUtil.parseToken(token);
        // String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> parmas){
        // 1.参数校验
        String oldPwd = parmas.get("old_pwd");
        String newPwd = parmas.get("new_pwd");
        String rePwd = parmas.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        // 原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        System.out.println(oldPwd);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }

        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一致");
        }

        // 2.调用service完成密码更新
        userService.updatePwd(newPwd);

        return Result.success();
    }
}

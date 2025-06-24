package com.euansu.interceptors;

import com.euansu.utils.JwtUtil;
import com.euansu.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        // 验证token
        try{
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 不报错，放行
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e){
            // http 响应状态码为401
            response.setStatus(401);
            // 不放行
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成，清空 ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}

package com.euansu.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
// lombok 在编译阶段为实体类自动生成setter、getter、toString
@Data
public class User {
    @NotNull
    private Integer id;
    private String username;
    @JsonIgnore // 让SpringMVC把当前对象转换成JSON字符串的时候，忽略password，最终的JSON字符串中就没有password这个属性了
    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;
    @NotEmpty
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
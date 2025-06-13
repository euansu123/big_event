package com.euansu.pojo;

import lombok.Data;
import java.time.LocalDateTime;
// lombok 在编译阶段为实体类自动生成setter、getter、toString
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
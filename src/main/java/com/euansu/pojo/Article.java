package com.euansu.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state; // 应该只允许 "已发布" 或 "草稿"
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
package com.euansu.service;

import com.euansu.pojo.Article;
import com.euansu.pojo.PageBean;

public interface ArticleService {
    // 新增文章
    void addArticle(Article article);
    // 条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
    // 更新文章
    void updateArticle(Article article);

    void delete(Integer id);
}

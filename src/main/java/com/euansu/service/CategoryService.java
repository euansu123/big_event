package com.euansu.service;

import com.euansu.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增文章分类
    void add(Category category);
    // 文章分类列表
    List<Category> list();
    // 文章分类详情
    Category findById(Integer id);
    // 文章分类更新
    void update(Category category);
    // 文章分类删除
    void deleteById(Integer id);
}

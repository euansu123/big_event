package com.euansu.controller;

import com.euansu.pojo.Article;
import com.euansu.pojo.PageBean;
import com.euansu.pojo.Result;
import com.euansu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private  ArticleService articleService;

    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        articleService.addArticle(article);
        return Result.success();
    }

    @PutMapping
    public Result updateArticle(@RequestBody @Validated Article article) {
        articleService.updateArticle(article);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        articleService.delete(id);
        return Result.success();
    }
}

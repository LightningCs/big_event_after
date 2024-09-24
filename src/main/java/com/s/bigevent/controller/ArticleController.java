package com.s.bigevent.controller;

import com.github.pagehelper.PageHelper;
import com.s.bigevent.domain.Article;
import com.s.bigevent.domain.PageBean;
import com.s.bigevent.domain.Result;
import com.s.bigevent.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, @RequestParam(required = false) String categoryId, @RequestParam(required = false) String state) {
        log.info("获取所有文章信息，{}，{}，{}，{}", pageNum, pageSize, categoryId, state);
        PageBean<Article> pages = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pages);
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        log.info("获取文章信息：{}", id);
        Article article = articleService.detail(id);
        return Result.success(article);
    }

    @PutMapping
    @CacheEvict(cacheNames = "articleCache_list")
    public Result update(@RequestBody Article article) {
        log.info("更新数据：{}", article);
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "articleCache_list")
    public Result delete(Integer id) {
        log.info("删除数据：{}", id);
        articleService.delete(id);
        return Result.success();
    }

    /**
     * 获取用户个人发表的文章
     * @param userId
     * @param pageSize
     * @return
     */
    @GetMapping("/individual")
    public Result<PageBean<Article>> getIndividualArticle(Integer userId, Integer pageSize) {
        log.info("获取用户个人发表的文章：{}，{}", userId, pageSize);

        PageBean<Article> individualArticles = articleService.getIndividualArticles(userId, pageSize);

        return Result.success(individualArticles);
    }
}

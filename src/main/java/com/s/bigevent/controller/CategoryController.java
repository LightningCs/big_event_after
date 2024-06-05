package com.s.bigevent.controller;

import com.s.bigevent.domain.Category;
import com.s.bigevent.domain.Result;
import com.s.bigevent.service.CategoryService;
import com.s.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService articleService;

    @PostMapping
    @CacheEvict(cacheNames = "categoryCache_list")
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("id"));
        articleService.add(category);
        return Result.success();
    }

    @GetMapping
    @Cacheable(cacheNames = "categoryCache_list")
    public Result<List<Category>> getList() {
        Map<String, Object> map = ThreadLocalUtil.get();
        List<Category> categories = articleService.getList((Integer) map.get("id"));
        return Result.success(categories);
    }

    @GetMapping("/detail")
    public Result<Category> detail(int id) {
        Category category = articleService.detail(id);
        return Result.success(category);
    }

    @PutMapping
    @CacheEvict(cacheNames = "categoryCache_list")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        articleService.update(category);
        return Result.success();
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "articleCache_list")
    public Result delete(@Validated(Category.Delete.class) int id) {
        articleService.delete(id);
        return Result.success();
    }
}

package com.s.bigevent.controller;

import com.s.bigevent.domain.Collection;
import com.s.bigevent.domain.Result;
import com.s.bigevent.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collect")
@Slf4j
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /**
     * 添加收藏
     * @param collection
     * @return
     */
    @PostMapping
    public Result collect(@RequestBody Collection collection) {
        log.info("文章收藏：{}", collection);

        collectionService.collect(collection);

        return Result.success();
    }

    /**
     * 根据用户id获取收藏集合
     * @param userId
     * @return
     */
    @GetMapping
    public Result<List<Integer>> getCollections(Integer userId) {
        log.info("根据用户id获取收藏集合：{}", userId);

        List<Integer> collections = collectionService.getByUserId(userId);

        return Result.success(collections);
    }

    /**
     * 取消收藏
     * @param collection
     * @return
     */
    @DeleteMapping
    public Result cancelCollect(@RequestBody Collection collection) {
        log.info("用户取消收藏：{}", collection);

        collectionService.cancelCollect(collection);

        return Result.success();
    }
}

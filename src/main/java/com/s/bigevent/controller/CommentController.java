package com.s.bigevent.controller;

import com.s.bigevent.constant.Constant;
import com.s.bigevent.domain.Comment;
import com.s.bigevent.domain.Result;
import com.s.bigevent.domain.dto.CommentDTO;
import com.s.bigevent.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CommentService commentService;

    /**
     * 通过文章id获取评论集
     * @param id
     * @return
     */
    @GetMapping
    public Result<List<Comment>> getCommentsByArticleId (Integer id) {
        log.info("根据文章id获取评论列表：{}", id);

        List<Comment> comments = commentService.getCommentsByArticleId(id);

        return Result.success(comments);
    }

    /**
     * 发布评论
     * @param commentDTO
     * @return
     */
    @PostMapping("/publish")
    public Result publishComment (@RequestBody CommentDTO commentDTO) {
        commentService.publishComment(commentDTO);

        return Result.success();
    }

    /**
     * 改变喜欢状态
     * @param commentId
     * @param userId
     * @return
     */
    @PutMapping("/{commentId}/{userId}/{like}")
    @Transactional
    public Result like (@PathVariable Integer commentId, @PathVariable Integer userId, @PathVariable Integer like) {
        log.info("喜欢，评论id：{}，用户id：{}，值：{}", commentId, userId, like);
        redisTemplate.opsForHash()
                .put(Constant.LIKE + userId, commentId, like % 3);

        commentService.update(commentId, like);

        return Result.success();
    }

    /**
     * 获取喜欢集合
     * @param userId
     * @return
     */
    @GetMapping("/like/{userId}")
    public Result<Map<String, Integer>> getLikeList(@PathVariable Integer userId) {
        log.info("获取喜欢集合：{}", userId);
        Map<String, Integer> likeList = redisTemplate.opsForHash()
                .entries(Constant.LIKE + userId);
        return Result.success(likeList);
    }
}

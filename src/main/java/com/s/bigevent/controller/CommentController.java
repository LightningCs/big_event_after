package com.s.bigevent.controller;

import com.s.bigevent.domain.Comment;
import com.s.bigevent.domain.dto.CommentDTO;
import com.s.bigevent.domain.Result;
import com.s.bigevent.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public Result<List<Comment>> getCommentsByArticleId (Integer id) {
        log.info("根据文章id获取评论列表：{}", id);

        List<Comment> comments = commentService.getCommentsByArticleId(id);

        return Result.success(comments);
    }

    @PostMapping("/publish")
    public Result publishComment (@RequestBody CommentDTO commentDTO) {
        commentService.publishComment(commentDTO);

        return Result.success();
    }
}

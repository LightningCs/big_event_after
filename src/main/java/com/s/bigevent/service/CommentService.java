package com.s.bigevent.service;

import com.s.bigevent.domain.Comment;
import com.s.bigevent.domain.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByArticleId(Integer id);

    void publishComment(CommentDTO commentDTO);
}

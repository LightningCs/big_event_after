package com.s.bigevent.service.impl;

import com.s.bigevent.domain.Comment;
import com.s.bigevent.domain.dto.CommentDTO;
import com.s.bigevent.mapper.CommentMapper;
import com.s.bigevent.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByArticleId(Integer id) {
        List<Comment> commentList = commentMapper.getByArticlesId(id);

        commentList.forEach(comment ->  {
            comment.setResponseList(commentMapper.getResponse(comment.getId()));
        });

        return commentList;
    }

    @Override
    public void publishComment(CommentDTO commentDTO) {
        commentMapper.publishComment(commentDTO);
    }

    @Override
    public void update(Integer commentId, Integer like) {
        commentMapper.update(commentId, like);
    }
}

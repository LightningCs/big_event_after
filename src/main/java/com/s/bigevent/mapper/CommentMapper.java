package com.s.bigevent.mapper;

import com.s.bigevent.domain.Comment;
import com.s.bigevent.domain.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select c.*, u.username as username, u.user_pic as userPic " +
            "from comment c left join user u on u.id = c.user_id where c.article_id = #{id} " +
            "order by c.`like`, c.dislike")
    List<Comment> getByArticlesId(Integer id);

    void publishComment(CommentDTO commentDTO);

    @Select("select * from comment where response_comment_id = #{commentId}")
    List<Comment> getResponse(Integer commentId);

    void update(Integer commentId, Integer like);
}

package com.s.bigevent.mapper;

import com.s.bigevent.domain.Comment;
import com.s.bigevent.domain.dto.CommentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select c.*, u.username as username, u.user_pic as userPic " +
            "from comment c left join user u on u.id = c.user_id where c.article_id = #{id}")
    List<Comment> getByArticlesId(Integer id);

    @Insert("insert into comment(content, article_id, user_id, create_time) " +
            "values(#{content}, #{articleId}, #{userId}, now())")
    void publishComment(CommentDTO commentDTO);
}

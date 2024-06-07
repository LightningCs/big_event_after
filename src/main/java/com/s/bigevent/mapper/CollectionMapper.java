package com.s.bigevent.mapper;

import com.s.bigevent.domain.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper {
    /**
     * 插入收藏数据
     * @param collection
     */
    @Insert("insert into collection(article_id, user_id) values(#{articleId}, #{userId});")
    void insert(Collection collection);

    /**
     * 根据用户id获取收藏集合
     * @param userId
     * @return
     */
    @Select("select article_id from collection where user_id = #{userId}")
    List<Integer> getByUserId(Integer userId);

    /**
     * 取消收藏
     * @param collection
     */
    @Delete("delete from collection where article_id = #{articleId} and user_id = #{userId};")
    void delete(Collection collection);

    /**
     * 根据用户id和文章id获取数据
     * @param collection
     * @return
     */
    @Select("select * from collection where article_id = #{articleId} and user_id = #{userId}")
    Collection getByArticleIdAndUserId(Collection collection);
}

package com.s.bigevent.mapper;

import com.s.bigevent.domain.dto.HistoryDTO;
import com.s.bigevent.domain.vo.HistoryVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface HistoryMapper {

    @Select("SELECT h.*, a.title AS title, a.cover_img AS cover_img, u.username AS create_username, u.user_pic AS user_pic, a.create_time AS create_time " +
            "FROM history h JOIN article a JOIN user u ON h.article_id = a.id AND h.create_user = u.id " +
            "WHERE h.user_id = #{userId} order by last_look_time desc")
    List<HistoryVO> getHistoriesByUserId(Integer userId);

    @Insert("insert into history (article_id, user_id, create_user, last_look_time) " +
            "values (#{articleId}, #{userId}, #{createUser}, #{lastLookTime});")
    void addHistory(HistoryDTO historyDTO);

    @Update("update history set last_look_time = #{lastLookTime} where id = #{id}")
    void updateHistory(HistoryDTO historyDTO);

    @Select("select id from history where article_id = #{articleId} and user_id = #{userId} and create_user = #{createUser}")
    String getHistoryByIds(HistoryDTO historyDTO);
}

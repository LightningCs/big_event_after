package com.s.bigevent.service;

import com.s.bigevent.domain.Collection;

import java.util.List;

public interface CollectionService {
    /**
     * 收藏文章
     * @param collection
     */
    void collect(Collection collection);

    /**
     * 根据用户id获取收藏集合
     * @param userId
     * @return
     */
    List<Integer> getByUserId(Integer userId);

    /**
     * 取消收藏
     * @param collection
     */
    void cancelCollect(Collection collection);

    /**
     * 根据用户id和文章id获取数据
     * @return
     */
    Collection getByArticleIdAndUserId(Collection collection);
}

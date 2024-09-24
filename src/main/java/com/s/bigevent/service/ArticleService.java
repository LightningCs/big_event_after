package com.s.bigevent.service;

import com.s.bigevent.domain.Article;
import com.s.bigevent.domain.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);

    Article detail(Integer id);

    void update(Article article);

    void delete(Integer id);

    PageBean<Article> getIndividualArticles(Integer userId, Integer pageSize);
}

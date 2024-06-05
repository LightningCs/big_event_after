package com.s.bigevent.service;

import com.s.bigevent.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    void add(Category category);

    List<Category> getList(Integer id);

    Category detail(int id);

    void update(Category category);

    void delete(int id);
}

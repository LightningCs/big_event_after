package com.s.bigevent.service.impl;

import com.s.bigevent.domain.Category;
import com.s.bigevent.mapper.CategoryMapper;
import com.s.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public List<Category> getList(Integer id) {
        return categoryMapper.getList(id);
    }

    @Override
    public Category detail(int id) {
        return categoryMapper.detail(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }
}

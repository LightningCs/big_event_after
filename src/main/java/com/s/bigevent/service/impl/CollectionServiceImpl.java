package com.s.bigevent.service.impl;

import com.s.bigevent.domain.Collection;
import com.s.bigevent.mapper.CollectionMapper;
import com.s.bigevent.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public void collect(Collection collection) {
        collectionMapper.insert(collection);
    }

    @Override
    public List<Integer> getByUserId(Integer userId) {
        return collectionMapper.getByUserId(userId);
    }

    @Override
    public void cancelCollect(Collection collection) {
        collectionMapper.delete(collection);
    }

    @Override
    public Collection getByArticleIdAndUserId(Collection collection) {
        return collectionMapper.getByArticleIdAndUserId(collection);
    }
}

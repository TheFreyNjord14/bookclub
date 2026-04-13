package com.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishlistItem entity) {
        
    }

    @Override
    public boolean remove(WishlistItem entity) {
        return false;
    }

    @Override
    public WishlistItem find(String key) {
        return null;
    }

}

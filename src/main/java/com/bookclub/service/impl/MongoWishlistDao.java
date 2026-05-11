package com.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<WishlistItem> list(String username) {
        Query query = new Query();

        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.find(query, WishlistItem.class);
    }

    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishlistItem entity) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(entity.getId()));

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + entity.getId());
        Update update = new Update();
        update.set("isbn", entity.getIsbn());
        update.set("title", entity.getTitle());
        update.set("username", entity.getUsername());
        mongoTemplate.findAndModify(query, update, WishlistItem.class);
    }

    @Override
    public void remove(String key) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(key));

        mongoTemplate.remove(query, WishlistItem.class);
    }

    @Override
    public WishlistItem find(String key) {
        WishlistItem wishlistItem = mongoTemplate.findById(key, WishlistItem.class);
        wishlistItem.setId(key);
        return wishlistItem;
    }

}

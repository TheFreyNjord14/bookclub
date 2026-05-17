package com.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao{
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public void add(BookOfTheMonth entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void remove(String key) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(key));

        mongoTemplate.remove(query, BookOfTheMonth.class);
    }

    @Override
    public List<BookOfTheMonth> list(String key) {
        int month = Integer.parseInt(key);

        if (month == 999)
        {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("month").is(month));
        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    @Override
    public BookOfTheMonth find(String key) {
        return mongoTemplate.findById(key, BookOfTheMonth.class);
    }
    
    @Override
    public void update(BookOfTheMonth entity) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(entity.getId()));

        mongoTemplate.findAndReplace(query, entity);
    }
}

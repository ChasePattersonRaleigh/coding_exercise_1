package com.interview.solution;

import com.mongodb.BasicDBObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
public class IntegrationTests {
    
    public void testOne(@Autowired MongoTemplate mongoTemplate) { 
        BasicDBObjectBuilder test;
    }
}

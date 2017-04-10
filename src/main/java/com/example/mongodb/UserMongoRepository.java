package com.example.mongodb;

import com.example.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by neal on 26/03/2017.
 */
public interface UserMongoRepository extends MongoRepository<UserMongo, String>{
    UserMongo findByUsername(String username);
}

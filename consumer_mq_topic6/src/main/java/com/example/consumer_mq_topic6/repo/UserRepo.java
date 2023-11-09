package com.example.consumer_mq_topic6.repo;

import com.example.consumer_mq_topic6.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public interface UserRepo extends MongoRepository<User, String> {
//
//}

@Repository
public interface UserRepo extends CrudRepository<User, String> {

}
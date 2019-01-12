package com.domain.repository;

import com.domain.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends MongoRepository<User, ObjectId> {

    // @Query("{}")
    List<User> findAll();

    List<User> findById(String id);

    List<User> findByFirstName(String lastName);
    
    List<User> findByLastName(String lastName);

    //User findByIdUser(String id);
}

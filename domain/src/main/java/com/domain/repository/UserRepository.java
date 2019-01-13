package com.domain.repository;

import com.domain.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findById(String id);

    List<User> findAll();

    List<User> findByFirstName(String firstName);
    
    List<User> findByLastName(String lastName);
}

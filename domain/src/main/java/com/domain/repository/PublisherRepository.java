package com.domain.repository;

import com.domain.entity.Publisher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends MongoRepository<Publisher, ObjectId> {

    Optional<Publisher> findById(String id);

    List<Publisher> findAll();

    List<Publisher> findByName(String name);
}

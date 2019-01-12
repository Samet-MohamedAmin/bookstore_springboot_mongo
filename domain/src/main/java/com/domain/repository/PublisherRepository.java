package com.domain.repository;

import com.domain.entity.Publisher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, ObjectId> {
}

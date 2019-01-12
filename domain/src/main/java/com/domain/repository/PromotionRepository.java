package com.domain.repository;

import com.domain.entity.Promotion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromotionRepository extends MongoRepository<Promotion, ObjectId> {
}

package com.domain.repository;

import com.domain.entity.Promotion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends MongoRepository<Promotion, ObjectId> {

    List<Promotion> findByDateStart(Date dateStart);
    List<Promotion> findByDateEnd(Date dateEnd);
}
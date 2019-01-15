package com.domain.repository;

import com.domain.entity.Discount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiscountRepository extends MongoRepository<Discount, ObjectId> {

    List<Discount> findByDateStart(LocalDate dateStart);

    List<Discount> findByDateEnd(LocalDate dateEnd);
}

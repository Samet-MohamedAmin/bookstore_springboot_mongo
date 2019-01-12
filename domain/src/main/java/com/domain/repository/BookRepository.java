package com.domain.repository;

import java.util.List;

import com.domain.entity.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, ObjectId> {

    List<Book> findById(String id);

    List<Book> findByName(String name);
}

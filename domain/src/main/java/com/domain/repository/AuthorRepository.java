package com.domain.repository;

import com.domain.entity.Author;
import com.domain.entity.Author;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, ObjectId> {

    Optional<Author> findById(String id);

    List<Author> findAll();

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);
}

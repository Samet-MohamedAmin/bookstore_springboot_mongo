package com.domain.repository;

import com.domain.entity.Person;

import com.domain.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {

    Optional<Person> findById(String id);

    List<Person> findAll();

    List<Person> findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);

}

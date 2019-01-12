package com.domain.repository;

import com.domain.entity.Person;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {

    List<Person> findAll();

    List<Person> findById(String id);

    List<Person> findByFirstName(String lastName);

    List<Person> findByLastName(String lastName);

}

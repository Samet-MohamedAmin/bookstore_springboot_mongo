package com.domain.entity;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@Service
public class User extends Person {

    private double budget;

    @DBRef
    private Order order;

    @Override
    public String toString() { return super.toString(); }
}
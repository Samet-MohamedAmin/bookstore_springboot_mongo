package com.domain.entity;


import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "user")
@Service
public class User extends Person {

    private double budget;

    @Override
    public String toString() {
        return super.toString();
    }
}
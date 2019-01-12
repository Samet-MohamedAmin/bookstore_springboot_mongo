package com.domain.entity;


import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;


@SuperBuilder
@NoArgsConstructor
@Document(collection = "person")
public class Publisher extends Person {

    private double budget;

    @Override
    public String toString() {
        return super.toString();
    }
}
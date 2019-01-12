package com.domain.entity;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;


@SuperBuilder
@NoArgsConstructor
@Document(collection = "author")
public class Author extends Person {

    @Override
    public String toString() {
        return super.toString();
    }
}
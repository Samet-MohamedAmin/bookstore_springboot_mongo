package com.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@SuperBuilder
@AllArgsConstructor
@Document(collection = "author")
public class Author extends Person {

    @Override
    @JsonIgnore
    public String toString() { return super.toString(); }
}
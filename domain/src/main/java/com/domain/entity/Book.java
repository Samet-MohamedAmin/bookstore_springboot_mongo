package com.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shared.annotation.CascadeSave;

import java.util.Set;


import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
// @CompoundIndexes({ @CompoundIndex(name = "name_year", def = "{'name' : 1, 'year': 1}") })
public class Book implements GeneralEntity {
    @Id
    private String id;
    @EqualsAndHashCode.Include
    private String name;
    @EqualsAndHashCode.Include
    private int year;
    private int quantity;
    private double price;

    @DBRef
    @CascadeSave
    private Set<Author> authorList;

    @DBRef
    @CascadeSave
    private Publisher publisher;

    @DBRef
    @CascadeSave
    private Discount discount;

    @Transient
    @JsonIgnore
    double getActualPrice() {

        return discount !=null && discount.isActive() ? price * (1 - discount.getPercentage()) : price;
    }
}

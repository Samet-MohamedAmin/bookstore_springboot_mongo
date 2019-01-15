package com.domain.entity;

import com.shared.annotation.CascadeSave;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
@CompoundIndexes({ @CompoundIndex(name = "name_year", def = "{'name' : 1, 'year': 1}") })
public class Book implements GeneralEntity {
    @Id
    private String id;
    private String name;
    private int year;
    private double price;
    private int quantity;

    @DBRef
    @CascadeSave
    private Set<Author> author;

    @DBRef
    @CascadeSave
    private Publisher publisher;

    @DBRef
    @CascadeSave
    private Promotion promotion;

    public void setId(String id){ this.id = id; }

    public double getActualPrice() {

        return price * promotion.getPercentage();
    }
}

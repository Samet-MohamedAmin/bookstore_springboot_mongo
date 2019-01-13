package com.domain.entity;

import com.shared.annotation.CascadeSave;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
public class Book implements GeneralEntity {
    @Id
    // @GeneratedValue(strategy= GenerationType.AUTO)
    private ObjectId id;
    private String name;
    private int year;
    private double price;


    @DBRef
    @CascadeSave
    private Set<Author> authors;


    // @DBRef
    // @CascadeSave
    // private Publisher publisher;
    private int quantity;

    // @ManyToMany(mappedBy = "booksCarts")
    // private List<Cart> carts ;
    // @ManyToMany(mappedBy = "orderBooks")
    // private List<Order> orders;

    public void setId(ObjectId id){
        this.id = id;
    }
}

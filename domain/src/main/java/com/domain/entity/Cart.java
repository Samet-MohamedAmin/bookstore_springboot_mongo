package com.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cart")
public class Cart implements GeneralEntity {

    @Id
    // @GeneratedValue(strategy= GenerationType.AUTO)
    private ObjectId id;

    public void setId(ObjectId id){
        this.id = id;
    }

    // @OneToOne(cascade = CascadeType.ALL)
    // private User user;
    // private float totalPrice;
    // @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // private List<Book> booksCarts;
}

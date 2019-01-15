package com.domain.entity;

import com.shared.annotation.CascadeSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cart")
public class Cart implements GeneralEntity {

    @Id
    private String id;
    private double totalPrice;

    @DBRef
    private User user;

    @DBRef
    @CascadeSave
    private List<Book> booksCarts;

    public void setId(String id){ this.id = id; }
}

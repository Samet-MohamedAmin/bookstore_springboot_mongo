package com.domain.entity;

import java.util.Date;

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
@Document(collection = "promotion")
public class Promotion implements GeneralEntity {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private ObjectId id;
    private double percentage;
    private Date debut;
    private Date fin;
    //@ManyToOne(cascade = CascadeType.ALL)
    private Book book;

    public void setId(ObjectId id){
        this.id = id;
    }
}

package com.domain.entity;

import java.util.Date;
import java.util.Set;

import com.shared.annotation.CascadeSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "promotion")
public class Promotion { //implements GeneralEntity {
    @Id
    private String id;
    private double percentage;
    private Date dateStart;
    private Date dateEnd;


    public void setId(String id){ this.id = id; }
}

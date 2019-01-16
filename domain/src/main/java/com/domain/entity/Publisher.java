package com.domain.entity;


import com.shared.annotation.CascadeSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "publisher")
public class Publisher implements GeneralEntity {

    @Id
    private String id;
    // @Indexed(unique=true)
    private String name;
    private String picture;

    public void setId(String id){ this.id = id; }
}

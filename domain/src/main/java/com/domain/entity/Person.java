package com.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "person")
@Service
public class Person {

    // public Person(String firstName, String lastName) {
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    // }

    @Id
    // @NonNull
    //@GeneratedValue(strategy= GenerationType.AUTO)
    ObjectId id;
    String firstName;
    String lastName;
    
    // public String getFullName(){
    //     return this.getFirstName() + " " + this.getLastName();
    // }
}

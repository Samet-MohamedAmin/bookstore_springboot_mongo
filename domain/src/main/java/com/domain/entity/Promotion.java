package com.domain.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "promotion")
public class Promotion {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private double percentage;
    private Date debut;
    private Date fin;
    //@ManyToOne(cascade = CascadeType.ALL)
    private Book book;
}

package com.domain.entity;

import com.shared.annotation.CascadeSave;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
@Component
public class Order implements GeneralEntity {

    @Id
    private String id;

    @DBRef
    @CascadeSave
    private List<Book> bookList;

    @DBRef
    LocalDateTime startDate = LocalDateTime.now();

    private double totalPrice;

    private boolean shipped = false;

    public void setId(String id){ this.id = id; }
}

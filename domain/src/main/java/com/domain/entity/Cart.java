package com.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shared.annotation.CascadeSave;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cart")
@Component
public class Cart implements GeneralEntity {

    @Id
    private String id;

    @DBRef
    @CascadeSave
    @Builder.Default
    private List<Book> bookList = new ArrayList<Book>();

    public void setId(String id){ this.id = id; }

    @JsonIgnore
    public final double getTotalPrice(){

        return bookList.stream()
                .reduce(0d,
                        (sum, book) -> sum += book.getActualPrice(),
                        (sum1, sum2) -> sum1 + sum2);
    }
}

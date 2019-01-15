package com.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@Service
public class User extends Person {

    private double budget;

    @DBRef
    private Cart cart;

    @DBRef
    private List<Order> orderList = new ArrayList<>();

    @Override
    public String toString() { return super.toString(); }

    @JsonIgnore
    public Order validateOrder(){


        Order order = null;
        if(cart.getTotalPrice() <= budget) {

            budget -= cart.getTotalPrice();
            order = Order.builder()
                                    .user(this)
                                    .bookList(cart.getBookList())
                                    .totalPrice(cart.getTotalPrice())
                                .build();
        }
        return order;
    }
}

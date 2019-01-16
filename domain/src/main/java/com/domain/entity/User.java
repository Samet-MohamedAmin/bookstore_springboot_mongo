package com.domain.entity;

import com.domain.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@SuperBuilder
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@Service
public class User extends Person {

    private double budget;

    @DBRef
    private Cart cart;

    @DBRef
    private List<Order> orderList;

    @Override
    public String toString() { return super.toString(); }

    @JsonIgnore
    public Optional<Order> validateOrder(){

        Order order = null;
        if(cart.getTotalPrice() <= budget) {

            budget -= cart.getTotalPrice();
            order =  Order.builder()
                            .bookList(cart.getBookList())
                            .totalPrice(cart.getTotalPrice())
                            .build();

            orderList.add(order);
        }
        return Optional.of(order);
    }
}

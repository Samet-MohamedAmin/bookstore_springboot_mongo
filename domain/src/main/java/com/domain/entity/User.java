package com.domain.entity;

import com.domain.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.shared.annotation.CascadeSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.log4j.Logger;
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
    @CascadeSave
    private Cart cart;

    @DBRef
    @CascadeSave
    @Builder.Default
    private List<Order> orderList = new ArrayList<>();

    @Override
    public String toString() { return super.toString(); }

    @JsonIgnore
    private static final Logger logger = Logger.getLogger(User.class);

    @JsonIgnore
    public Optional<Order> validateOrder(){

        logger.info("validate order");

        Order order = null;
        logger.info("total price: " + cart.getTotalPrice());
        if(cart.isAllBooksPresent() && cart.getTotalPrice() <= budget) {

            budget -= cart.getTotalPrice();
            order =  Order.builder()
                            .bookList(cart.getBookList())
                            .totalPrice(cart.getTotalPrice())
                            .build();
            logger.info(cart.getBookList());
            cart.getBookList().forEach(book -> book.setQuantity(book.getQuantity()-1));
            logger.info(cart.getBookList());
            cart.setBookList(new ArrayList<>());
            orderList.add(order);
        }
        return Optional.ofNullable(order);
    }
}

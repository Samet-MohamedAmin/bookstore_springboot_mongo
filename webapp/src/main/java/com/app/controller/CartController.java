package com.app.controller;

import com.domain.entity.Book;
import com.domain.entity.Cart;
import com.domain.repository.BookRepository;
import com.domain.repository.CartRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/carts", produces = "application/json")
public class CartController {
    private static final Logger logger = Logger.getLogger(CartController.class);


    private CartRepository cartRepository;

    GeneralController generalController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartController(CartRepository cartRepository) {

        this.cartRepository = cartRepository;
        this.generalController = new GeneralController<Cart>(cartRepository);
    }


    /*
     * get all carts
     */
    @GetMapping(path="")
    public ResponseEntity<List<Cart>> getAllCarts() {

        return generalController.getAll();
    }

    /*
     * get cart accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all carts according to firstName
     *
    @GetMapping(path="/get")
    public ResponseEntity<List<Cart>> getCartsByAttribute(
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName) {

        List<Cart> carts;
        if(firstName != null) {
            logger.info("firstName: " + firstName);
            carts = cartRepository.findByFirstName(firstName);
        }
        else if (lastName != null) {
            logger.info("lastName");
            carts = cartRepository.findByLastName(lastName);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
    */


    /*
     * save a new cart
     */
    @PostMapping(path="/add")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {

        return generalController.saveObject(cart);
    }

    /*
     * update an existing cart
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<Cart> updateCart(
            @PathVariable(required=true) String id,
            @RequestBody Cart cart) {

        return generalController.updateObject(id, cart);
    }


    /*
     * add books to cart
     */
    @PutMapping(path="/update/{id}/book_list/add", consumes="application/json")
    public ResponseEntity<List<Book>> addBooks(
            @PathVariable(required=true) String id,
            @RequestBody  BookList bookListToAdd) {

        Optional<Cart> result = generalController.findById(id);

        if(!result.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        List<Book> bookList = result.get().getBookList();
        bookList.addAll(bookListToAdd.getBookList());
        cartRepository.save(result.get());
        return new ResponseEntity<>(bookList, HttpStatus.ACCEPTED);
    }


    /*
     * remove books from cart
     */
    @PutMapping(path="/update/{id}/book_list/remove", consumes="application/json")
    public ResponseEntity<List<Book>> removeBooks(
            @PathVariable(required=true) String id,
            @RequestBody  BookList BookListToRemove) {

        List<Book> booksToRemove = BookListToRemove.getBookList();

        logger.info(booksToRemove);

        Optional<Cart> result = generalController.findById(id);

        if(!result.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Book> bookList = result.get().getBookList();
        bookList.removeAll(booksToRemove);
        result.get().setBookList(bookList);
        cartRepository.save(result.get());
        return new ResponseEntity<>(bookList, HttpStatus.ACCEPTED);
    }


    /*
     * delete cart
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Cart> removeCart(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}

@Document
@ToString
@Getter
class BookList {

    private List<Book> bookList;
}
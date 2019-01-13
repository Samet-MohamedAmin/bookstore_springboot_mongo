package com.app.controller;

import com.domain.entity.User;
import com.domain.repository.UserRepository;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.domain.entity.Book;
import com.domain.repository.BookRepository;




@RestController
@RequestMapping(path = "/api/books", produces = "application/json")
class BookController {
    static final Logger logger = Logger.getLogger(BookController.class);


    BookRepository bookRepository;

    GeneralController generalController;

    @Autowired
    private BookController(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
        this.generalController = new GeneralController<Book>(bookRepository);
    }


    /*
     * get all books
     */
    @GetMapping(path="/get/all")
    public ResponseEntity<List<Book>> getAllBooks() {

        return generalController.getAll();
    }

    /*
     * get book accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<List<Book>> getBookById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all books according to firstName
     */
    @GetMapping(path="/get")
    public ResponseEntity<List<Book>> getBooksByAttribute(
        @RequestParam(required=false) String name) {
        
        List<Book> books;
        if(!name.isEmpty()) books = bookRepository.findByName(name);
        else return new ResponseEntity<List<Book>>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }


    /*
     * save a new book
     */
    @PostMapping(path="/add", consumes="application/json")
    public ResponseEntity<List<Book>> saveBook(@RequestBody Book book) {

        return generalController.saveObject(book);
    }

    /*
     * update an existing book
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<List<Book>> updateBook(
        @PathVariable(required=true) String id,
        @RequestBody Book book) {
        
        return generalController.updateObject(id, book);
    }

    /*
     * delete book
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<List<Book>> removeBook(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }
}
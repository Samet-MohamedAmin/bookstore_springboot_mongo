package com.app.controller;

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
    final static Logger logger = Logger.getLogger(BookController.class);
    
    @Autowired
    BookRepository bookRepository;


    /*
     * get all books
     */
    @GetMapping(path="/get/all")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    /*
     * get book accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<List<Book>> getBookById(@PathVariable(required=true) String id){

        List<Book> books = bookRepository.findById(id);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
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

        bookRepository.save(book);
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
    }

    /*
     * update an existing book
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<List<Book>> updateBook(
        @PathVariable(required=true) String id,
        @RequestBody Book newBook) {
        
        List<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            logger.info("book with id id " + id + "does not exist");
            return new ResponseEntity<List<Book>>(book, HttpStatus.BAD_REQUEST);
        }

        newBook.setId(new ObjectId(id));
        bookRepository.save(newBook);

        List<Book> listBook = new ArrayList<Book>();
        listBook.add(newBook);

        return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
    }

    /*
     * delete book
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<List<Book>> removeBook(@PathVariable(required=true) String id) {

        List<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            logger.info("book with id id " + id + "does not exist");
            return new ResponseEntity<List<Book>>(book, HttpStatus.BAD_REQUEST);
        }
        bookRepository.delete(book.get(0));
        return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
    }
}
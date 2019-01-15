package com.app.controller;

import com.domain.entity.Author;
import com.domain.repository.AuthorRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/authors", produces = "application/json")
public class AuthorController {
    private static final Logger logger = Logger.getLogger(AuthorController.class);


    private AuthorRepository authorRepository;

    GeneralController generalController;

    @Autowired
    private AuthorController(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
        this.generalController = new GeneralController<Author>(authorRepository);
    }


    /*
     * get all authors
     */
    @GetMapping(path="")
    public ResponseEntity<List<Author>> getAllAuthors() {

        return generalController.getAll();
    }

    /*
     * get author accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all authors according to firstName
     */
    @GetMapping(path="/get")
    public ResponseEntity<List<Author>> getAuthorsByAttribute(
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName) {

        List<Author> authors;
        if(firstName != null) {
            logger.info("firstName: " + firstName);
            authors = authorRepository.findByFirstName(firstName);
        }
        else if (lastName != null) {
            logger.info("lastName");
            authors = authorRepository.findByLastName(lastName);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    /*
     * save a new author
     */
    @PostMapping(path="/add")
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {

        return generalController.saveObject(author);
    }

    /*
     * update an existing author
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable(required=true) String id,
            @RequestBody Author author) {

        return generalController.updateObject(id, author);
    }

    /*
     * delete author
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Author> removeAuthor(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
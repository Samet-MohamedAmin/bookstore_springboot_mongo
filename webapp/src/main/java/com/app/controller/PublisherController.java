package com.app.controller;

import com.domain.entity.Publisher;
import com.domain.repository.PublisherRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/publishers", produces = "application/json")
public class PublisherController {
    private static final Logger logger = Logger.getLogger(PublisherController.class);


    private PublisherRepository publisherRepository;

    GeneralController generalController;

    @Autowired
    private PublisherController(PublisherRepository publisherRepository) {

        this.publisherRepository = publisherRepository;
        this.generalController = new GeneralController<Publisher>(publisherRepository);
    }


    /*
     * get all publishers
     */
    @GetMapping(path="")
    public ResponseEntity<List<Publisher>> getAllPublishers() {

        return generalController.getAll();
    }

    /*
     * get publisher accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all publishers according to firstName
     */
    @GetMapping(path="/get")
    public ResponseEntity<List<Publisher>> getPublishersByAttribute(
            @RequestParam(required=false) String name) {

        List<Publisher> publishers;
        if(name != null) {
            logger.info("name: " + name);
            publishers = publisherRepository.findByName(name);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }


    /*
     * save a new publisher
     */
    @PostMapping(path="/add")
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {

        return generalController.saveObject(publisher);
    }

    /*
     * update an existing publisher
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<Publisher> updatePublisher(
            @PathVariable(required=true) String id,
            @RequestBody Publisher publisher) {

        return generalController.updateObject(id, publisher);
    }

    /*
     * delete publisher
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Publisher> removePublisher(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
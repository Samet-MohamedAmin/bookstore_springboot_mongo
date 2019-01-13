package com.app.controller;

import com.domain.entity.User;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;




public class GeneralController<DomainClass> {
    private static final Logger logger = Logger.getLogger(UserController.class);
    // private final Class<?> domainClass;

    private MongoRepository<DomainClass, ObjectId> repo;

    GeneralController(MongoRepository<DomainClass, ObjectId> repository){

        this.repo = repository;
        // this.domainClass = domainClass;
    }


    /*
     * get all objects
     */
    public ResponseEntity<List<DomainClass>> getAll() {

        List<DomainClass> objects = repo.findAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * get user accroding to id
     *
    @GetMapping(path="/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(required=true) String id){

        Optional<User> result =  userRepository.findById(id);
        return result.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /*
     * get all users according to firstName
     *
    @GetMapping(path="/get")
    public ResponseEntity<List<User>> getUsersByAttribute(
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName) {

        return new ResponseEntity<>(userRepository.findByFirstName("Amine"), HttpStatus.OK

        );

        /*
        List<User> users;
        if(firstName != null) {
            logger.info("firstName");
            users = userRepository.findByFirstName(firstName);
            logger.info(users);
        }
        else if (lastName != null) {
            logger.info("lastName");
            users = userRepository.findByLastName(lastName);
            logger.info(users);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    /*
     * save a new user
     *
    @PostMapping(path="/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*
     * update an existing user
     *
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<User> updateUser(
            @PathVariable(required=true) String id,
            @RequestBody User newUser) {

        Optional<User> result = userRepository.findById(id);

        if(!result.isPresent()) {
            logger.info("user with id id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        newUser.setId(new ObjectId(id));
        userRepository.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
    }

    /*
     * delete user
     *
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<User> removeUser(@PathVariable(required=true) String id) {

        Optional<User> result = userRepository.findById(id);
        if(!result.isPresent()) {
            logger.info("user with id id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        userRepository.delete(result.get());
        return new ResponseEntity<>(result.get(), HttpStatus.ACCEPTED);
    }*/

}
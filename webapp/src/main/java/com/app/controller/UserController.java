package com.app.controller;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.domain.repository.UserRepository;
import com.domain.entity.User;



@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
public class UserController {
    final static Logger logger = Logger.getLogger(UserController.class);
    
     @Autowired
     UserRepository userRepository;


     /*
      * get all users
      */
     @GetMapping(path="/get/all")
     public ResponseEntity<List<User>> getAllUsers() {

         List<User> users = userRepository.findAll();
         return new ResponseEntity<List<User>>(users, HttpStatus.OK);
     }

    /*
     * get user accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<List<User>> getUserById(@PathVariable(required=true) String id){

        List<User> users = userRepository.findById(id);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /*
     * get all users according to firstName
     */
    @GetMapping(path="/get")

    
    public ResponseEntity<List<User>> getUsersByAttribute(
        @RequestParam(required=false) String firstName,
        @RequestParam(required=false) String lastName) {
        
        List<User> users;
        if(!firstName.isEmpty()) users = userRepository.findByFirstName(firstName);
        else if (!lastName.isEmpty()) users = userRepository.findByLastName(lastName);
        else return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    /*
     * save a new user
     */
    @PostMapping(path="/add")
    public ResponseEntity<List<User>> saveUser(@RequestBody User user) {
        
        try {
            userRepository.save(user);
            List<User> userList = new ArrayList<User>();
            userList.add(user);
            return new ResponseEntity<List<User>>(userList, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*
     * update an existing user
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<List<User>> updateUser(
        @PathVariable(required=true) String id,
        @RequestBody User newUser) {
        
        List<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            logger.info("user with id id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.OK);
        }

        newUser.setId(new ObjectId(id));
        userRepository.save(newUser);

        List<User> listUser = new ArrayList<User>();
        listUser.add(newUser);

        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    /*
     * delete user
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<List<User>> removeUser(@PathVariable(required=true) String id) {

        List<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            logger.info("user with id id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        userRepository.delete(user.get(0));
        return new ResponseEntity<List<User>>(user, HttpStatus.NOT_ACCEPTABLE);
    }
}
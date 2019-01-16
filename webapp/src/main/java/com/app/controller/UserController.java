package com.app.controller;

import com.domain.entity.Book;
import com.domain.entity.Cart;
import com.domain.entity.Order;
import com.domain.repository.CartRepository;
import com.domain.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.domain.repository.UserRepository;
import com.domain.entity.User;



@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);


    private UserRepository userRepository;

    GeneralController generalController;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
        this.generalController = new GeneralController<User>(userRepository);
    }


    /*
     * get all users
     */
    @GetMapping(path="")
    public ResponseEntity<List<User>> getAllUsers() {

        return generalController.getAll();
    }

    /*
     * get user accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all users according to firstName
     */
    @GetMapping(path="/get")
    public ResponseEntity<List<User>> getUsersByAttribute(
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName) {

        List<User> users;
        if(firstName != null) {
            logger.info("firstName: " + firstName);
            users = userRepository.findByFirstName(firstName);
        }
        else if (lastName != null) {
            logger.info("lastName");
            users = userRepository.findByLastName(lastName);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    /*
     * save a new user
     */
    @PostMapping(path="/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        user.setCart(cartRepository.save(Cart.builder().build()));

        return generalController.saveObject(user);
    }

    /*
     * update an existing user
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<User> updateUser(
            @PathVariable(required=true) String id,
            @RequestBody User user) {

        return generalController.updateObject(id, user);
    }

    /*
     * validate order (cart -> order)
     */
    @PutMapping(path="/update/{id}/validate_order", consumes="application/json")
    public ResponseEntity<Order> removeBooks(@PathVariable(required=true) String id) {

        Optional<User> resultUser = generalController.findById(id);

        // TODO: should return NOT_ACCEPTED when budget < totalPrice

        if(!resultUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<Order> resultOrder = resultUser.get().validateOrder();
        if(!resultOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        orderRepository.save(resultOrder.get());
        userRepository.save(resultUser.get());
        return new ResponseEntity<>(resultOrder.get(), HttpStatus.ACCEPTED);
    }

    /*
     * delete user
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<User> removeUser(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
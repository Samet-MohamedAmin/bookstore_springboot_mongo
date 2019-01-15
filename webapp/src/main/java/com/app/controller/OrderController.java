package com.app.controller;

import com.domain.entity.Order;
import com.domain.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
public class OrderController {
    private static final Logger logger = Logger.getLogger(OrderController.class);


    private OrderRepository orderRepository;

    GeneralController generalController;

    @Autowired
    private OrderController(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
        this.generalController = new GeneralController<Order>(orderRepository);
    }


    /*
     * get all orders
     */
    @GetMapping(path="")
    public ResponseEntity<List<Order>> getAllOrders() {

        return generalController.getAll();
    }

    /*
     * get order accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all orders according to firstName
     *
    @GetMapping(path="/get")
    public ResponseEntity<List<Order>> getOrdersByAttribute(
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName) {

        List<Order> orders;
        if(firstName != null) {
            logger.info("firstName: " + firstName);
            orders = orderRepository.findByFirstName(firstName);
        }
        else if (lastName != null) {
            logger.info("lastName");
            orders = orderRepository.findByLastName(lastName);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    */


    /*
     * save a new order
     */
    @PostMapping(path="/add")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {

        return generalController.saveObject(order);
    }

    /*
     * update an existing order
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<Order> updateOrder(
            @PathVariable(required=true) String id,
            @RequestBody Order order) {

        return generalController.updateObject(id, order);
    }

    /*
     * delete order
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
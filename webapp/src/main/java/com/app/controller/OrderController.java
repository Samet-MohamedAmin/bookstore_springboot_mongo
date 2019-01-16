package com.app.controller;

import com.domain.entity.Book;
import com.domain.entity.Order;
import com.domain.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
     * validate shipment
     */
    @PutMapping(path="/update/{id}/validate_shipment", consumes="application/json")
    public ResponseEntity<Order> addBooks(@PathVariable(required=true) String id) {

        Optional<Order> result = generalController.findById(id);

        if(!result.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        Order order = result.get();
        order.setShipped(true);
        orderRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }


    /*
     * remove books from order
     */
    @PutMapping(path="/update/{id}/book_list/remove", consumes="application/json")
    public ResponseEntity<List<Book>> removeBooks(
            @PathVariable(required=true) String id,
            @RequestBody  BookList BookListToRemove) {

        List<Book> booksToRemove = BookListToRemove.getBookList();

        Optional<Order> result = generalController.findById(id);

        if(!result.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Book> bookList = result.get().getBookList();
        bookList.removeAll(booksToRemove);
        result.get().setBookList(bookList);
        orderRepository.save(result.get());
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }


    /*
     * delete order
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
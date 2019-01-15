package com.app.controller;

import com.domain.entity.Discount;
import com.domain.repository.DiscountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "/api/discounts", produces = "application/json")
public class DiscountController {
    private static final Logger logger = Logger.getLogger(DiscountController.class);


    private DiscountRepository discountRepository;

    GeneralController generalController;

    @Autowired
    private DiscountController(DiscountRepository discountRepository) {

        this.discountRepository = discountRepository;
        this.generalController = new GeneralController<Discount>(discountRepository);
    }


    /*
     * get all discounts
     */
    @GetMapping(path="")
    public ResponseEntity<List<Discount>> getAllDiscounts() {

        return generalController.getAll();
    }

    /*
     * get discount accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all discounts according to firstName
     */
    @GetMapping(path="/get")
    public ResponseEntity<List<Discount>> getDiscountsByAttribute(
            @RequestParam(required=false) LocalDate dateStart,
            @RequestParam(required=false) LocalDate dateEnd) {

        List<Discount> discounts;
        if(dateStart != null) {
            logger.info("firstName: " + dateStart);
            discounts = discountRepository.findByDateStart(dateStart);
        }
        else if (dateEnd != null) {
            logger.info("lastName");
            discounts = discountRepository.findByDateEnd(dateEnd);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }


    /*
     * save a new discount
     */
    @PostMapping(path="/add")
    public ResponseEntity<Discount> saveDiscount(@RequestBody Discount discount) {

        if(!discount.isDateValid()) {

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return generalController.saveObject(discount);
    }

    /*
     * update an existing discount
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<Discount> updateDiscount(
            @PathVariable(required=true) String id,
            @RequestBody Discount discount) {

        return generalController.updateObject(id, discount);
    }

    /*
     * delete discount
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Discount> removeDiscount(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
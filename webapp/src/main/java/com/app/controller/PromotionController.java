package com.app.controller;

import com.domain.entity.Promotion;
import com.domain.repository.PromotionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "/api/promotions", produces = "application/json")
public class PromotionController {
    private static final Logger logger = Logger.getLogger(PromotionController.class);


    private PromotionRepository promotionRepository;

    GeneralController generalController;

    @Autowired
    private PromotionController(PromotionRepository promotionRepository) {

        this.promotionRepository = promotionRepository;
        this.generalController = new GeneralController<Promotion>(promotionRepository);
    }


    /*
     * get all promotions
     */
    @GetMapping(path="")
    public ResponseEntity<List<Promotion>> getAllPromotions() {

        return generalController.getAll();
    }

    /*
     * get promotion accroding to id
     */
    @GetMapping(path="/get/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable(required=true) String id){

        return generalController.getById(id);
    }

    /*
     * get all promotions according to firstName
     */
    @GetMapping(path="/get")
    public ResponseEntity<List<Promotion>> getPromotionsByAttribute(
            @RequestParam(required=false) Date dateStart,
            @RequestParam(required=false) Date dateEnd) {

        List<Promotion> promotions;
        if(dateStart != null) {
            logger.info("firstName: " + dateStart);
            promotions = promotionRepository.findByDateStart(dateStart);
        }
        else if (dateEnd != null) {
            logger.info("lastName");
            promotions = promotionRepository.findByDateEnd(dateEnd);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }


    /*
     * save a new promotion
     */
    @PostMapping(path="/add")
    public ResponseEntity<Promotion> savePromotion(@RequestBody Promotion promotion) {

        if(promotion.getDateStart().after(promotion.getDateEnd())) {

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return generalController.saveObject(promotion);
    }

    /*
     * update an existing promotion
     */
    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<Promotion> updatePromotion(
            @PathVariable(required=true) String id,
            @RequestBody Promotion promotion) {

        return generalController.updateObject(id, promotion);
    }

    /*
     * delete promotion
     */
    @DeleteMapping(path="/remove/{id}")
    public ResponseEntity<Promotion> removePromotion(@PathVariable(required=true) String id) {

        return generalController.removeObject(id);
    }

}
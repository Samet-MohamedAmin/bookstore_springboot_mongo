package com.app.controller;

import com.domain.entity.GeneralEntity;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;



// curl -X $m $a1$a2 -H $h -d $d
class GeneralController<DomainClass> {
    private static final Logger logger = Logger.getLogger(GeneralController.class);

    private MongoRepository<DomainClass, ObjectId> domainRepository;

    GeneralController(MongoRepository<DomainClass, ObjectId> domainRepository){

        this.domainRepository = domainRepository;
    }

    /*
     * get all objects
     */
    ResponseEntity<List<DomainClass>> getAll() {

        List<DomainClass> objects = domainRepository.findAll();
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    /*
     * get object accroding to id
     */
    ResponseEntity<DomainClass> getById(String id){

        Optional<DomainClass> result =  domainRepository.findById(new ObjectId(id));
        return result.map(object -> new ResponseEntity<>(object, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    /*
     * save a new object
     */
     ResponseEntity<DomainClass> saveObject(DomainClass object) {

        try {
            domainRepository.save(object);
            return new ResponseEntity<>(object, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*
     * update an existing object
     */
    ResponseEntity<DomainClass> updateObject(String id, DomainClass object) {

        Optional<DomainClass> result = domainRepository.findById(new ObjectId(id));

        if(!result.isPresent()) {
            logger.info("object with id id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        ((GeneralEntity)object).setId(new ObjectId(id));

        domainRepository.save(object);

        return new ResponseEntity<>(object, HttpStatus.ACCEPTED);
    }

    /*
     * delete object
     */
    ResponseEntity<DomainClass> removeObject(String id) {

        Optional<DomainClass> result = domainRepository.findById(new ObjectId(id));
        if(!result.isPresent()) {
            logger.info("object with id id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        domainRepository.delete(result.get());
        return new ResponseEntity<>(result.get(), HttpStatus.ACCEPTED);
    }

}
package com.app.scheduled;

import com.domain.entity.Discount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

// @Component
public class ScheduledJobs {

    static final Logger logger = Logger.getLogger(Scheduled.class);


    @Autowired
    MongoTemplate mongoTemplate;

    // @Scheduled(fixedRate = 5000)
    public void autoremoveDiscount(){

        Query query = new Query(Criteria.where("dateEnd").lt(LocalDate.now()));
        List<Discount> discounts = mongoTemplate.findAllAndRemove(query, Discount.class);

        logger.info(discounts);
    }
}

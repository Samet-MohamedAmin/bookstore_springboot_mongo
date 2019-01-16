package com.app;


import com.domain.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.domain.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner{
    
    private static final Logger logger = Logger.getLogger(DataLoader.class);


    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("hello");

        // clear database when starting the app
        // mongoTemplate.getDb().drop();




        //LocalDate dateStart = LocalDate.of(2019, Month.JANUARY, 10);

        /*
        LocalDate dateEnd_1 = LocalDate.of(2019, Month.JANUARY, 10);
        LocalDate dateEnd_2 = LocalDate.of(2019, Month.JANUARY, 12);
        LocalDate dateEnd_3 = LocalDate.of(2019, Month.JANUARY, 16);
        LocalDate dateEnd_4 = LocalDate.of(2019, Month.JANUARY, 20);


        Discount discount_1 = Discount.builder()
                                        .dateEnd(dateEnd_1)
                                        .build();

        Discount discount_2 = Discount.builder()
                                        .dateEnd(dateEnd_2)
                                        .build();

        Discount discount_3 = Discount.builder()
                                        .dateEnd(dateEnd_3)
                                        .build();


        Discount discount_4 = Discount.builder()
                                        .dateEnd(dateEnd_4)
                                        .build();


        discountRepository.save(discount_1);
        discountRepository.save(discount_2);
        discountRepository.save(discount_3);
        discountRepository.save(discount_4);
        */

        /*
        User user = User.builder().firstName("User").lastName("Good").build();
        UserRepository.save(user);

        Author author1 = Author.builder().firstName("author_1").build();
        Author author2 = Author.builder().firstName("author_2").build();
        Author author3 = Author.builder().firstName("author_3").build();

        // authorRepository.save(author1);
        // authorRepository.save(author2);
        // authorRepository.save(author3);


        Set<Author> authors = new HashSet<Author>();
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        logger.info(authors);

        Book theBookThief = Book.builder().name("The Book Thief").authors(authors).build();
        
        logger.info(theBookThief);

        bookRepository.save(theBookThief);
        */
    }
}

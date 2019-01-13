package com.app;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.domain.entity.*;
import com.domain.repository.*;

import com.shared.Mared;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;
    
    static final Logger logger = Logger.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("hello");

        Mared.printIt();

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

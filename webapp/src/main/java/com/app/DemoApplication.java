package com.app;

import com.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.Logger;


@SpringBootApplication
public class DemoApplication {

	@Autowired
	Person person;

	final static Logger logger = Logger.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}
}

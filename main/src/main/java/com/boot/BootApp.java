package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com"})
@EntityScan("com.domain.entity")
@EnableMongoRepositories("com.domain.repository")
public class BootApp {

	final static Logger logger = Logger.getLogger(BootApp.class);

	public static void main(String[] args) {
		
		SpringApplication.run(BootApp.class, args);
	}
}

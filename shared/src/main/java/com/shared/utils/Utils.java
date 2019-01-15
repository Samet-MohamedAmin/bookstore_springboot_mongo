package com.shared.utils;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    private Utils(){}

    // @Scheduled(fixedRate = 5000)
    public void printBla(){

        logger.info("bla");
    }

    public static String makeJSON(String key, String value) {

        return String.format("{\"%s\":\"%s\"}", key, value);
    }
}

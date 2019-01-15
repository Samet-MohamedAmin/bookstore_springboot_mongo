package com.shared.utils;

import org.apache.log4j.Logger;


public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    private Utils(){}


    public static String makeJSON(String key, String value) {

        return String.format("{\"%s\":\"%s\"}", key, value);
    }
}

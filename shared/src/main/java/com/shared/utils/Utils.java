package com.shared.utils;


public class Utils {

    private Utils(){}

    public static String makeJSON(String key, String value) {

        return String.format("{\"%s\":\"%s\"}", key, value);
    }
}

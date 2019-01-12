package com.shared.utils;


public class Utils {


    public static String makeJSON(String key, String value) {

        return String.format("{\"%s\":\"%s\"}", key, value);
    }
}
package com.app.controller;

import com.shared.utils.Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "", produces = "application/json")
class HomeController {

    /*
     * home mapping
     */
    @GetMapping(path = "/home")
    public ResponseEntity<String> homeMessage() {

        // List<String> list = new ArrayList<String>();
        // list.add("blaa");
        String result = Utils.makeJSON("message", "home");
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    /*
     * test mapping
     */
    @GetMapping(path = "/test")
    public ResponseEntity<String> testMessage() {

        // List<String> list = new ArrayList<String>();
        // list.add("blaa");
        String result = Utils.makeJSON("message", "test");
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

}

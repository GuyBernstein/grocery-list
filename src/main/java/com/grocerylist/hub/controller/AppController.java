package com.grocerylist.hub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getClicks() {
        return "hello";
    }
}

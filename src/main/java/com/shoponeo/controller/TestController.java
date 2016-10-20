package com.shoponeo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tomasz on 20.10.2016.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
        return "it work so much";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login page is here";
    }
}

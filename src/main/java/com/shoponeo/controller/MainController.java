package com.shoponeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomasz on 25.11.2016.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String mainView() {
        return "main";
    }
}

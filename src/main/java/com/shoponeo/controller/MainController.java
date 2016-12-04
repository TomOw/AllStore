package com.shoponeo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomasz on 25.11.2016.
 */
@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/")
    public String mainView() {
        logger.info("getting main view");
        return "main";
    }
}

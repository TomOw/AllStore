package com.shoponeo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Tomasz on 20.12.2016.
 */
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("/me")
    public Principal getLoggedInUser(Principal principal) {
        return principal;
    }
}

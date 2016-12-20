package com.shoponeo.controller;

import com.shoponeo.model.User;
import com.shoponeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/me")
    public User getLoggedInUser(Principal principal) {
        return userRepository.get(principal.getName());
    }
}

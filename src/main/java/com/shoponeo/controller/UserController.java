package com.shoponeo.controller;

import com.shoponeo.model.User;
import com.shoponeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tomasz on 24.12.2016.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add")
    public User addUser(@RequestBody User user) {
        userRepository.add(user);
        return user;
    }

    @RequestMapping(value = "/edit")
    public User editUser(@RequestBody User user) {
        userRepository.update(user);
        return user;
    }

    @RequestMapping(value = "/all")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @RequestMapping(value = "/get/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userRepository.get(username);
    }
}

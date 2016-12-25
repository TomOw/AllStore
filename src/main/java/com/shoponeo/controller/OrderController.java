package com.shoponeo.controller;

import com.shoponeo.model.shop.Cart;
import com.shoponeo.model.shop.Order;
import com.shoponeo.repository.UserRepository;
import com.shoponeo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tomasz on 25.12.2016.
 */

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public List<Order> makeOrderFromCart(@RequestBody Cart cart) {
        return orderService.divideOrderToStores(cart, userRepository.get("admin"));
    }
}

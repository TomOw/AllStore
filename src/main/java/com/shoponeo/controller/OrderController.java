package com.shoponeo.controller;

import com.shoponeo.model.User;
import com.shoponeo.model.shop.Cart;
import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Order;
import com.shoponeo.repository.OrderRepository;
import com.shoponeo.repository.StoreRepository;
import com.shoponeo.repository.UserRepository;
import com.shoponeo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public Order makeOrderFromCart(@RequestBody Cart cart) {
        Order order = new Order();
        order.setDate(new Date());
        User user = userRepository.get("user");
        List<Item> items = cart.getItems();
        order.setUser(user);
        user.addOrder(order);
        int storeId = items.get(0).getStoreId();
        storeRepository.addOrder(storeRepository.getStoreById(storeId), order, items);
        return order;
    }

    @RequestMapping(value = "/get/{username}")
    public List<Order> getOrdersByUsername(@PathVariable("username") String username) {
        return orderRepository.getOrdersByUsername(username);
    }
}
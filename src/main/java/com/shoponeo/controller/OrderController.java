package com.shoponeo.controller;

import com.shoponeo.model.User;
import com.shoponeo.model.shop.Cart;
import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Order;
import com.shoponeo.repository.StoreRepository;
import com.shoponeo.repository.UserRepository;
import com.shoponeo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

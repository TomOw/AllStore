package com.shoponeo.controller;

import com.shoponeo.model.User;
import com.shoponeo.model.shop.Address;
import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Order;
import com.shoponeo.model.shop.Store;
import com.shoponeo.repository.OrderRepository;
import com.shoponeo.repository.StoreRepository;
import com.shoponeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Tomasz on 20.11.2016.
 */
@RestController
@RequestMapping(value = "/store")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/address/{storeName}")
    public Address getStoreAddresByStoreName(@PathVariable("storeName") String name) {
        return storeRepository.getStoreAddressByStoreName(name);
    }

    @RequestMapping(value = "/byName/{storeName}")
    public Store getStoreByName(@PathVariable("storeName") String name) {
        Store store = storeRepository.getStoreByName(name).get(0);
        store.setOrders(null);
        return store;
    }

    @RequestMapping(value = "/orders/{storeName}")
    public List<Order> getOrdersFromStore(@PathVariable("storeName") String name) {
        List<Order> orders = orderRepository.getOrdersByStoreName(name);
        for (Order order :
                orders) {
            for (Item item :
                    order.getItems()) {
                item.setReviews(null);
                item.setDescription("");
            }
        }
        return orders;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void editStore(@RequestBody Store store) {
        storeRepository.editStore(store);
    }

    @RequestMapping(value = "/allNames", method = RequestMethod.GET)
    public List<String> getAllStoreNames() {
        return storeRepository.getAllStoreNames();
    }

    @RequestMapping(value = "/owner", method = RequestMethod.GET)
    public Store getStoreByOwner(Principal principal) {
        String username = principal.getName();
        User user = userRepository.get(username);
        return storeRepository.getStoreByOwner(user.getUsername());
    }
}

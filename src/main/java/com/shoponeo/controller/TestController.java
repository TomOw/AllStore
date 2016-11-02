package com.shoponeo.controller;

import com.shoponeo.model.shop.*;
import com.shoponeo.repository.ItemRepository;
import com.shoponeo.repository.OrderRepository;
import com.shoponeo.repository.ReviewRepository;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 20.10.2016.
 */
@RestController
public class TestController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/test")
    public String test() {
        return "it work so much";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login page is here";
    }

    @RequestMapping(value = "/store")
    public Store testStore() {
        Store store = new Store("eigth", 3.5, 45, 45);
        StoreAddress address = new StoreAddress("BR", "RIO", "SUPER", "32", "00333");
        store.setStoreAddress(address);
        address.setStore(store);
        Item item = new Item("komputr", 5500, "category", "super cos", "photo", 500, 6.0);
        Item item2 = new Item("laptok", 323, "category", "sztos cos", "photo2", 5002, 4.0);
        store.getItems().add(item);
        store.getItems().add(item2);
        item.setStore(store);
        item2.setStore(store);
        storeRepository.addStore(store);
        return store;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/store/{name}")
    public List<Store> getStore(@PathVariable("name") String name) {
        List<Store> list = storeRepository.getStoreByName(name);
        Store store = list.get(0);
        //System.out.println(store.getItems());
        return list;
    }

    @RequestMapping(value = "/addItem")
    public Item add() {
        Store store = storeRepository.getStoreByName("eigth").get(0);
        Item item = new Item("czajnik", 100.99, "category3", "tablety", "photo", 400, 6.0);
        storeRepository.addItemToStore(store, item);
        return item;
    }

    @RequestMapping(value = "/item/{name}")
    public List<Item> getItems(@PathVariable("name") String name) {
        return itemRepository.getItemsByName(name);
    }

    @RequestMapping(value = "/category/{category}")
    public List<Item> getItemsByCategory(@PathVariable("category") String category) {
        return itemRepository.getItemsByCategory(category);
    }

    @RequestMapping(value = "/rev")
    public Item review() {
        //Item item = new Item("komputr", 5500, "category", "super cos", "photo", 500, 6.0);
        Item item = itemRepository.getItemsByName("laptok").get(0);
        Review review = new Review("desc");
        reviewRepository.addReviewToItem(item, review);
        return item;
    }

    @RequestMapping(value = "/or")
    public Order order() {
        Order order = new Order();
        //Item item = new Item("tea", 40, "cat", "desc", "photo", 500, 5.0);
        List<Item> list = new ArrayList<>();
        Item item = itemRepository.getItemsByName("czajnik").get(0);
        list.add(item);
        list.add(itemRepository.getItemsByName("szklanka").get(0));
        item.addOrder(order);
        order.getItems().add(item);
        order.calculatePrice();
        storeRepository.addOrder(storeRepository.getStoreByName("eigth").get(0), order, list);
        return order;
    }

    @RequestMapping(value = "/orstore")
    public List<Order> o() {
        return orderRepository.getOrdersByStoreName("eigth");
    }
}

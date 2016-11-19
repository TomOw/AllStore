package com.shoponeo.controller;

import com.shoponeo.model.shop.*;
import com.shoponeo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CategoryRepository categoryRepository;

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
        Store store = new Store();
        store.setName("Satcom");
        store.setCountry("USA");
        store.setCity("New York");
        store.setStreet("5th");
        store.setNumber("43");
        store.setPostalCode("13414");
        storeRepository.addStore(store);
        Store store1 = new Store();
        store1.setName("Microsoft Store");
        store1.setCountry("USA");
        store1.setCity("San Francisco");
        store1.setStreet("Elizabeth");
        store1.setNumber("100");
        store1.setPostalCode("65984");
        storeRepository.addStore(store1);
        Store store2 = new Store();
        store2.setName("ETD");
        store2.setCountry("PL");
        store2.setCity("Warszawa");
        store2.setStreet("Srebrna");
        store2.setNumber("15");
        store2.setPostalCode("00323");
        storeRepository.addStore(store2);
        Store store3 = new Store();
        store3.setName("E-MALL");
        store3.setCountry("PL");
        store3.setCity("Poznan");
        store3.setStreet("Ludwika");
        store3.setNumber("41");
        store3.setPostalCode("50580");
        storeRepository.addStore(store3);
        return store;
    }

    @RequestMapping(value = "/store/{name}")
    public Store getStore(@PathVariable("name") String name) {
        List<Store> list = storeRepository.getStoreByName(name);
        Store store = list.get(0);
        //System.out.println(store.getItems());
        return store;
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

    @RequestMapping(value = "/categories")
    public List<String> c() {
        return categoryRepository.getAllCategories();
    }

    @RequestMapping(value = "/storys")
    public List<String> strings() {
        return storeRepository.getAllStoreNames();
    }

    @RequestMapping(value = "/viewsUp/{itemId}")
    public int viewsUp(@PathVariable("itemId") int itemId) {
        Item item = itemRepository.getItemById(itemId);
        return itemRepository.increaseViews(item);
    }

    @RequestMapping(value = "/itemStores")
    public List<Integer> itemStores() {
        return itemRepository.getStoreIdsByItemId("iPhone 7 Plus");
    }

    @RequestMapping(value = "/itemNameStores")
    public List<Store> itemNameStores() {
        return storeRepository.getStoresByItemName("iPhone 7 Plus");
    }
}

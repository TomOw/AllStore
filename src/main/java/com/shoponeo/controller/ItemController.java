package com.shoponeo.controller;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Offer;
import com.shoponeo.model.shop.Review;
import com.shoponeo.repository.ItemRepository;
import com.shoponeo.repository.ReviewRepository;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Tomasz on 16.11.2016.
 */

@RestController
@RequestMapping(value = "/item")
@PreAuthorize("isAuthenticated()")
public class ItemController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(value = "/add/{store}", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item item, @PathVariable("store") String storeName) {
        System.out.println(item);
        itemRepository.randomizePrice(item);
        itemRepository.randomizeNoInStock(item);
        storeRepository.addItemToStore(storeRepository.getStoreByName(storeName).get(0), item);
        return item;
    }

    @RequestMapping(value = "/add/list", method = RequestMethod.POST)
    public List<Item> addItemList(@RequestBody List<Item> itemList) {
        return storeRepository.addItemListToStore(itemList);
    }

    @RequestMapping(value = "/byId/{itemId}")
    public Item getItemById(@PathVariable("itemId") int id) {
        Item itemById = itemRepository.getItemById(id);
        System.out.println(itemById);
        return itemById;
    }

    @RequestMapping(value = "/byName/{itemName}")
    public Set<Item> getItemsByName(@PathVariable("itemName") String name) {
        List<Item> itemsByName = itemRepository.getItemsByName(name);
        Set<Item> itemSet = new HashSet<>(itemsByName);
        return itemSet;
    }

    @RequestMapping(value = "/byCategory/{categoryName}")
    public Set<Item> getItemsByCategory(@PathVariable("categoryName") String categoryName) {
        List<Item> itemList = itemRepository.getItemsByCategory(categoryName);
        Set<Item> itemSet = new HashSet<>(itemList);
        return itemSet;
    }

    @RequestMapping(value = "/cheapest/{itemName}")
    public Offer getCheapest(@PathVariable("itemName") String name) {
        List<Offer> list = storeRepository.getOffers(name);
        Collections.sort(list);
        return list.get(0);
    }

    @RequestMapping(value = "/offers/{itemName}")
    public List<Offer> getOffers(@PathVariable("itemName") String name) {
        return storeRepository.getOffers(name);
    }

    @RequestMapping(value = "/reviews/{itemName}")
    public List<Review> getItemReviewsByItemName(@PathVariable("itemName") String name) {
        return reviewRepository.getReviewsByItemName(name);
    }

}

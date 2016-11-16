package com.shoponeo.controller;

import com.shoponeo.model.shop.Item;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomasz on 16.11.2016.
 */

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    StoreRepository storeRepository;

    @RequestMapping(value = "/add/{store}", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item item, @PathVariable("store") String storeName) {
        storeRepository.addItemToStore(storeRepository.getStoreByName(storeName).get(0), item);
        return item;
    }
}

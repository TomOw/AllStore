package com.shoponeo.controller;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.StoreItemPrice;
import com.shoponeo.repository.ItemRepository;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tomasz on 16.11.2016.
 */

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/add/{store}", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item item, @PathVariable("store") String storeName) {
        System.out.println(item);
        storeRepository.addItemToStore(storeRepository.getStoreByName(storeName).get(0), item);
        return item;
    }

    @RequestMapping(value = "/{itemId}")
    public Item getItemById2222(@PathVariable("itemId") int id) {
        Item itemById = itemRepository.getItemById(id);
        System.out.println(itemById);
        return itemById;
    }

    @RequestMapping(value = "/cheapest/{itemName}")
    public StoreItemPrice getCheapest(@PathVariable("itemName") String name) {
        List<StoreItemPrice> list = storeRepository.getStoreItemPrice(name);
        Collections.sort(list);
        return list.get(0);
    }
}

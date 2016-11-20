package com.shoponeo.controller;

import com.shoponeo.model.shop.Address;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tomasz on 20.11.2016.
 */
@RestController
@RequestMapping(value = "/store")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @RequestMapping(value = "/address/{storeName}")
    public Address getStoreAddresByStoreName(@PathVariable("storeName") String name) {
        return storeRepository.getStoreAddressByStoreName(name);
    }
}

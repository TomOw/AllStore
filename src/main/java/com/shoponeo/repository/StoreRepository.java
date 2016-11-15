package com.shoponeo.repository;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Order;
import com.shoponeo.model.shop.Store;
import com.shoponeo.model.shop.StoreAddress;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by owczatom on 2016-10-21.
 */
@Repository
@Transactional
public interface StoreRepository {

    Store addStore(Store store);

    Store addStore(Store store, StoreAddress storeAddress);

    Store getStoreById(int id);

    List<Store> getStoreByName(String name);

    Item addItemToStore(Store store, Item item);

    public Order addOrder(Store store, Order order, List<Item> items);

    List<String> getAllStoreNames();
}

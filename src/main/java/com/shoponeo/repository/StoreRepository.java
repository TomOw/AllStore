package com.shoponeo.repository;

import com.shoponeo.model.shop.*;
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

    Store getStoreById(int id);

    List<Store> getStoreByName(String name);

    Item addItemToStore(Store store, Item item);

    List<Item> addItemListToStore(List<Item> itemList);

    Order addOrder(Store store, Order order, List<Item> items);

    List<String> getAllStoreNames();

    List<Store> getStoresByItemName(String name);

    List<Offer> getOffers(String name);

    Address getStoreAddressByStoreName(String name);

    Store editStore(Store store);

}

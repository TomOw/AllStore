package com.shoponeo.repository;

import com.shoponeo.model.shop.Item;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by owczatom on 2016-10-21.
 */
@Repository
@Transactional
public interface ItemRepository {

    List<Item> getItemsByName(String name);

    Item getItemById(int id);

    List<Item> getItemsByCategory(String category);

    int increaseViews(Item item);

    List<Integer> getStoreIdsByItemId(String name);

    Item randomizePrice(Item item);

    Item randomizeNoInStock(Item item);

    void deleteItem(Item item);

    Item editItem(Item item);

    Set<Item> selectRandomItems(int count);
}

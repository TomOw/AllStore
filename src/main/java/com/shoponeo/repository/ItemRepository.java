package com.shoponeo.repository;

import com.shoponeo.model.shop.Item;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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
}

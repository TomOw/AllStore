package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by owczatom on 2016-10-21.
 */
@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Item> getItemsByName(String name) {
        Query query = entityManager.createQuery("from Item where name = :name");
        query.setParameter("name", name);
        List<Item> items = query.getResultList();
        return items;
    }

    @Override
    public Item getItemById(int id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public List<Item> getItemsByCategory(String category) {
        Query query = entityManager.createQuery("from Item where category = :category");
        query.setParameter("category", category);
        List<Item> items = query.getResultList();
        return items;
    }

    @Override
    public int increaseViews(Item item) {
        int views = item.getViews();
        views += 1;
        item.setViews(views);
        entityManager.merge(item);
        return views;
    }

    @Override
    public List<Integer> getStoreIdsByItemId(String name) {
        Query query = entityManager.createQuery("select item.store.id from Item item where item.name = :itemName");
        query.setParameter("itemName", name);
        List<Integer> list = query.getResultList();
        return list;
    }
}

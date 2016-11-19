package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Order;
import com.shoponeo.model.shop.Store;
import com.shoponeo.repository.StoreRepository;
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
public class StoreRepositoryImpl implements StoreRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Store addStore(Store store) {
        entityManager.persist(store);
        return store;
    }

    @Override
    public Store getStoreById(int id) {
        return entityManager.find(Store.class, id);
    }

    @Override
    public List<Store> getStoreByName(String name) {
        Query query = entityManager.createQuery("from Store where name = :name");
        query.setParameter("name", name);
        List<Store> stores = query.getResultList();
        return stores;
    }

    @Override
    public Item addItemToStore(Store store, Item item) {
        store.getItems().add(item);
        item.setStore(store);
        entityManager.merge(store);
        return item;
    }

    @Override
    public Order addOrder(Store store, Order order, List<Item> items) {
        for (Item item :
                items) {
            item.addOrder(order);
            item.setStore(store);
            store.addItem(item);
            order.getItems().add(item);
        }
        store.addOrder(order);
        order.setStore(store);
        entityManager.merge(store);
        return order;
    }

    @Override
    public List<String> getAllStoreNames() {
        Query query = entityManager.createQuery("select store.name from Store store");
        List<String> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Store> getStoresByItemName(String name) {
        Query query = entityManager.createQuery("select store.name from Store store inner join store.items as item where item.name = :name");
        query.setParameter("name", name);
        List<Store> list = query.getResultList();
        return query.getResultList();
    }
}

package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Store;
import com.shoponeo.model.shop.StoreAddress;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Store addStore(Store store, StoreAddress storeAddress) {
        store.setStoreAddress(storeAddress);
        storeAddress.setStore(store);
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
}

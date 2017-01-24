package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.*;
import com.shoponeo.repository.ItemRepository;
import com.shoponeo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;

/**
 * Created by owczatom on 2016-10-21.
 */
@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class StoreRepositoryImpl implements StoreRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ItemRepository itemRepository;

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
    public List<Item> addItemListToStore(List<Item> itemList) {
        for (Item item :
                itemList) {
            Store store = getStoreByName(item.getStoreName()).get(0);
            addItemToStore(store, item);
        }
        return itemList;
    }

    @Override
    public Order addOrder(Store store, Order order, List<Item> items) {
        for (Item item :
                items) {
            item.addOrder(order);
            item.setStore(store);
            item.setNoInStock(item.getNoInStock() - 1);
            itemRepository.editItem(item);
            //store.addItem(item);
            //order.addItem(item);
        }
        order.setItems(items);
        order.calculatePrice();
        store.addOrder(order);
        order.setStore(store);
        store.setNoOfOrdersMade(store.getNoOfOrdersMade() + 1);
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

    @Override
    public List<Offer> getOffers(String name) {
        Query query = entityManager.createQuery("select new com.shoponeo.model.shop.Offer(store.name, store.id, store.country, store.city, store.street, store.number, store.postalCode, item.id, item.price, item.noInStock) from Store store inner join store.items as item where item.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Address getStoreAddressByStoreName(String name) {
        Query query = entityManager.createQuery("select new com.shoponeo.model.shop.Address(store.country, store.city, store.street, store.number, store.postalCode) from Store store where store.name = :name");
        query.setParameter("name", name);
        return (Address) query.getResultList().get(0);
    }

    @Override
    public Store editStore(Store store) {
        Query query = entityManager.createQuery("update Store set name = :name, country = :country, city = :city, postalCode = :postalCode where id = :id");
        query.setParameter("name", store.getName());
        query.setParameter("country", store.getCountry());
        query.setParameter("city", store.getCity());
        query.setParameter("postalCode", store.getPostalCode());
        query.setParameter("id", store.getId());
        query.executeUpdate();
        return store;
    }

    @Override
    public Store getStoreByOwner(String ownerUsername) {
        Query query = entityManager.createQuery("select store from Store store join store.owners owner where owner.username = :name");
        query.setParameter("name", ownerUsername);
        return (Store) query.getResultList().get(0);
    }
}

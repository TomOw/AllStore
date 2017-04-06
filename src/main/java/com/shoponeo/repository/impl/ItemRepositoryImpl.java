package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Item> getItemsByName(String name) {
        Query query = entityManager.createQuery("select item from Item item where item.name like :name order by item.price asc");
        query.setParameter("name", name + "%");
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

    @Override
    public Item randomizePrice(Item item) {
        SecureRandom secureRandom = new SecureRandom();
        double x = item.getPrice();
        double diff = secureRandom.nextDouble() * x * 20 / 100;
        if (secureRandom.nextInt(2) == 1) {
            item.setPrice(Math.round((x + diff) * 10.0) / 10.0);
        } else {
            item.setPrice(Math.round((x - diff) * 10.0) / 10.0);
        }
        return item;
    }

    @Override
    public Item randomizeNoInStock(Item item) {
        SecureRandom secureRandom = new SecureRandom();
        int x = item.getNoInStock();
        int diff = secureRandom.nextInt(x * 2 / 3);
        if (secureRandom.nextInt(2) == 1) {
            item.setNoInStock(Math.round(x + diff));
        } else {
            item.setNoInStock(Math.round(x - diff));
        }
        return item;
    }

    @Override
    public void deleteItem(Item item) {
        entityManager.refresh(item);
    }

    @Override
    public Item editItem(Item item) {
        entityManager.merge(item);
        return item;
    }

    @Override
    public Set<Item> selectRandomItems(int count) {
        Query query = entityManager.createQuery("select item from Item item where item.name like :name order by rand()");
        query.setParameter("name", "%");
        query.setMaxResults(count);
        List<Item> items = query.getResultList();
        Set<Item> itemSet = new HashSet<>(items);
        return itemSet;
    }
}

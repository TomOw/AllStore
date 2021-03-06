package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Order;
import com.shoponeo.repository.OrderRepository;
import org.hibernate.jpa.criteria.FromImplementor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomasz on 01.11.2016.
 */
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Order addOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public List<Order> addOrderList(List<Order> orders) {
        for (Order order :
                orders) {
            entityManager.persist(order);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStoreName(String name) {
        Query query = entityManager.createQuery("from Order where store.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        Query query = entityManager.createQuery("from Order where user.username = :username");
        query.setParameter("username", username);
        return query.getResultList();
    }
}

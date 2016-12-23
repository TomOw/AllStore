package com.shoponeo.repository;

import com.shoponeo.model.shop.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomasz on 01.11.2016.
 */
@Repository
@Transactional
public interface OrderRepository {

    public Order addOrder(Order order);

    public List<Order> getOrdersByStoreName(String name);

    public List<Order> getOrdersByUsername(String username);
}

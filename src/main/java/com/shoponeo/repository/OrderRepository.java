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

    Order addOrder(Order order);

    List<Order> getOrdersByStoreName(String name);

    List<Order> getOrdersByUsername(String username);
}

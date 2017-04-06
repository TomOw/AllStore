package com.shoponeo.repository;

import com.shoponeo.model.shop.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository {

    Order addOrder(Order order);

    List<Order> addOrderList(List<Order> orders);

    List<Order> getOrdersByStoreName(String name);

    List<Order> getOrdersByUsername(String username);
}

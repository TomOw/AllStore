package com.shoponeo.service.impl;

import com.shoponeo.model.User;
import com.shoponeo.model.shop.Cart;
import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Order;
import com.shoponeo.repository.StoreRepository;
import com.shoponeo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    StoreRepository storeRepository;

    public boolean isStoreNameUniqueInOrders(List<Order> orders, String storeName) {
        for (Order order :
                orders) {
            if ("".equals(storeName)) {
                return false;
            }
        }
        return true;
    }

    public Order getOrderByStoreName(List<Order> orders, String storeName) {
        for (Order order :
                orders) {
            if ("".equals(storeName)) {
                return order;
            }
        }
        throw new IllegalArgumentException("order with provided storeName does not exist in this list");
    }

    @Override
    public List<Order> divideOrderToStores(Cart cart, User user) {
        List<Order> orders = new ArrayList<>();
        List<Item> items = cart.getItems();
        for (Item item :
                items) {
            try {
                Order order = getOrderByStoreName(orders, item.getStoreName());
                order.addItem(item);
                order.calculatePrice();
            } catch (IllegalArgumentException e) {
                Order order = new Order();
                order.setDate(new Date());
                order.setUser(user);
                order.setStore(storeRepository.getStoreByName(item.getStoreName()).get(0));
                order.addItem(item);
                order.calculatePrice();
                orders.add(order);
            }
        }
        return orders;
    }
}

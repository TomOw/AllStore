package com.shoponeo.service;

import com.shoponeo.model.User;
import com.shoponeo.model.shop.Cart;
import com.shoponeo.model.shop.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tomasz on 25.12.2016.
 */
@Service
public interface OrderService {

    List<Order> divideOrderToStores(Cart cart, User user);
}

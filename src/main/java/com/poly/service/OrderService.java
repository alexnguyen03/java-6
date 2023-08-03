package com.poly.service;

import java.util.List;

import com.poly.model.Account;
import com.poly.model.Order;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    List<Order> findAllCancel();
    Account findAllAccount(Long id);

    List<Order> findByUsername(String username);

    List<Order> findAllCancelByUsername(String username);

    Order save(Order order);
}

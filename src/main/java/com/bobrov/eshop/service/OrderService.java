package com.bobrov.eshop.service;

import com.bobrov.eshop.model.Order;

import java.util.List;

public interface OrderService {
    Order findById(Long id);

    List<Order> findAll();
}

package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.OrderRepository;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.model.Order;
import com.bobrov.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}

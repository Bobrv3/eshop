package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.OrderRepository;
import com.bobrov.eshop.dto.OrderDto;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.mapper.OrderMapper;
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
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return OrderMapper.INSTANCE.toDto(order);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.INSTANCE.toListDto(orders);
    }
}

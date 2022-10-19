package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.OrderRepository;
import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.OrderDto;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.mapper.OrderMapper;
import com.bobrov.eshop.model.Order;
import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.Product;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

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

    @Override
    public OrderDto save(OrderDto order) {
        Order newOrder = new Order();
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setStatus(Order.OrderStatus.IN_PROCESSING);
        User user = userRepository.findByUsername(order.getUser().getUsername())
                .orElseThrow(NotFoundException::new);
        newOrder.setUser(user);
        newOrder.setOrderDetails(order.getOrderDetails()
                .stream()
                .map(orderDetail -> {
                    Product product = productRepository.findById(orderDetail.getProduct().getId())
                            .orElseThrow(NotFoundException::new);
                    OrderDetail newOrderDetail = new OrderDetail();
                    newOrderDetail.setProduct(product);
                    newOrderDetail.setOrder(newOrder);
                    newOrderDetail.setQuantity(orderDetail.getQuantity());
                    return newOrderDetail;
                })
                .collect(Collectors.toList()));

        Order savedOrder = orderRepository.save(newOrder);

        return OrderMapper.INSTANCE.toDto(savedOrder);
    }
}

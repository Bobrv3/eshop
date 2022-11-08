package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.OrderDetailRepository;
import com.bobrov.eshop.dao.OrderRepository;
import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.OrderDto;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.exception.OutOfStockException;
import com.bobrov.eshop.exception.ProductNotFoundException;
import com.bobrov.eshop.exception.UserNotFoundException;
import com.bobrov.eshop.mapper.OrderDetailMapper;
import com.bobrov.eshop.mapper.OrderMapper;
import com.bobrov.eshop.model.Order;
import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.Product;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository detailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return OrderMapper.INSTANCE.toDto(order);
    }

    @Override
    public List<Order> findAll(Integer offset, Integer limit) {
        Page<Order> orders = orderRepository.findAll(PageRequest.of(offset, limit));
        return orders.getContent();
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setStatus(Order.OrderStatus.IN_PROCESSING);

        User user = userRepository.findByUsername(orderDto.getUser().getUsername())
                .orElseThrow(UserNotFoundException::new);
        newOrder.setUser(user);

        orderDto.getOrderDetails().stream()
                .forEach(orderDetail -> {
                    Product product = productRepository.findById(orderDetail.getProduct().getId())
                            .orElseThrow(ProductNotFoundException::new);

                    if (product.getStatus() == Product.ProductStatus.OUT_OF_STOCK) {
                        throw new OutOfStockException(String.format("%s is out of stock", product.getName()));
                    }

                    OrderDetail newOrderDetail = new OrderDetail();
                    newOrderDetail.setProduct(product);
                    newOrderDetail.setQuantity(orderDetail.getQuantity());

                    newOrder.addOrderDetail(newOrderDetail);
                });

        return OrderMapper.INSTANCE.toDto(
                orderRepository.save(newOrder)
        );
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Optional<Order> orderOptional = orderRepository.findById(orderDto.getId());
        if (!orderOptional.isPresent()) {
            return save(orderDto);
        }

        Order order = orderOptional.get();

        order.removeAllDetails();

        User user = userRepository.findByUsername(orderDto.getUser().getUsername())
                .orElseThrow(UserNotFoundException::new);
        order.setUser(user);

        orderDto.getOrderDetails().stream()
                .forEach(orderDetail -> {
                    Product product = productRepository.findById(orderDetail.getId().getProductId())
                            .orElseThrow(ProductNotFoundException::new);
                    if (product.getStatus() == Product.ProductStatus.OUT_OF_STOCK) {
                        throw new OutOfStockException(String.format("%s is out of stock", product.getName()));
                    }

                    OrderDetail newOrderDetail = OrderDetailMapper.INSTANCE.toDetail(orderDetail);
                    order.addOrderDetail(newOrderDetail);
                    newOrderDetail.setProduct(product);
                    newOrderDetail.setQuantity(orderDetail.getQuantity());
                });

        return OrderMapper.INSTANCE.toDto(
                orderRepository.save(order)
        );
    }

    @Override
    public void delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(String.format("No order with id %s exists", id));
        }
    }
}

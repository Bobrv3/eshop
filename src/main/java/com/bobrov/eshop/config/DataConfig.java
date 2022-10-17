package com.bobrov.eshop.config;

import com.bobrov.eshop.dao.OrderDetailRepository;
import com.bobrov.eshop.dao.OrderRepository;
import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.model.Order;
import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.OrderDetailId;
import com.bobrov.eshop.model.Product;
import com.bobrov.eshop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DataConfig {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            userRepository.save(new User(1L, "admin", "$2a$12$YhY/MmkgGspmZi6P.7fG5O04OISr7C1.hlEs7TNlZO1cro90wn5Re", "+375447931700", "admin@gmail.com", User.Role.ROLE_ADMIN, false, true));
            userRepository.save(new User(2L, "user", "$2a$12$oS.MoiwXtb8YQjxrLNuBPubtc3QmOxzTM53YOZS6r.3zEvVd.ZqfK", "+375443875454", "user@gmail.com", User.Role.ROLE_USER, false, true));

            Random random = new Random();
            productRepository.save(new Product(1L, "Milk", new BigDecimal(1.45), Product.ProductStatus.values()[random.nextInt(Product.ProductStatus.values().length)], LocalDateTime.now()));
            productRepository.save(new Product(2L, "Bread", new BigDecimal(0.99), Product.ProductStatus.values()[random.nextInt(Product.ProductStatus.values().length)], LocalDateTime.now()));
            productRepository.save(new Product(3L, "Butter", new BigDecimal(1.55), Product.ProductStatus.values()[random.nextInt(Product.ProductStatus.values().length)], LocalDateTime.now()));
            productRepository.save(new Product(4L, "Sausage", new BigDecimal(2.39), Product.ProductStatus.values()[random.nextInt(Product.ProductStatus.values().length)], LocalDateTime.now()));

            orderRepository.save(Order.builder()
                    .user(userRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .status(Order.OrderStatus.values()[random.nextInt(Order.OrderStatus.values().length)])
                    .build());

            orderRepository.save(Order.builder()
                    .user(userRepository.findById(2L).orElseThrow(NotFoundException::new))
                    .status(Order.OrderStatus.values()[random.nextInt(Order.OrderStatus.values().length)])
                    .build());

            orderRepository.save(Order.builder()
                    .user(userRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .status(Order.OrderStatus.values()[random.nextInt(Order.OrderStatus.values().length)])
                    .build());

            orderRepository.save(Order.builder()
                    .user(userRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .status(Order.OrderStatus.values()[random.nextInt(Order.OrderStatus.values().length)])
                    .build());

            orderDetailRepository.save(OrderDetail.builder()
                    .id(new OrderDetailId())
                    .product(productRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .order(orderRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .quantity(2)
                    .build());

            orderDetailRepository.save(OrderDetail.builder()
                    .id(new OrderDetailId())
                    .product(productRepository.findById(2L).orElseThrow(NotFoundException::new))
                    .order(orderRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .quantity(1)
                    .build());

            orderDetailRepository.save(OrderDetail.builder()
                    .id(new OrderDetailId())
                    .product(productRepository.findById(2L).orElseThrow(NotFoundException::new))
                    .order(orderRepository.findById(2L).orElseThrow(NotFoundException::new))
                    .quantity(1)
                    .build());

            orderDetailRepository.save(OrderDetail.builder()
                    .id(new OrderDetailId())
                    .product(productRepository.findById(3L).orElseThrow(NotFoundException::new))
                    .order(orderRepository.findById(3L).orElseThrow(NotFoundException::new))
                    .quantity(1)
                    .build());

            orderDetailRepository.save(OrderDetail.builder()
                    .id(new OrderDetailId())
                    .product(productRepository.findById(1L).orElseThrow(NotFoundException::new))
                    .order(orderRepository.findById(4L).orElseThrow(NotFoundException::new))
                    .quantity(1)
                    .build());

            orderDetailRepository.save(OrderDetail.builder()
                    .id(new OrderDetailId())
                    .product(productRepository.findById(4L).orElseThrow(NotFoundException::new))
                    .order(orderRepository.findById(4L).orElseThrow(NotFoundException::new))
                    .quantity(2)
                    .build());
        };
    }
}

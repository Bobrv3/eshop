package com.bobrov.eshop.controller;

import com.bobrov.eshop.model.Order;
import com.bobrov.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrder() {
        return orderService.findAll();
    }
}

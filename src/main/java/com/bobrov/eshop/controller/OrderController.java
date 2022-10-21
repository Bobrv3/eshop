package com.bobrov.eshop.controller;

import com.bobrov.eshop.dto.OrderDto;
import com.bobrov.eshop.mapper.OrderMapper;
import com.bobrov.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAll(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        return OrderMapper.INSTANCE.toListDto(
                orderService.findAll(offset, limit)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PutMapping
    public OrderDto update(@RequestBody OrderDto orderDto) {
        return orderService.update(orderDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}

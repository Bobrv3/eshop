package com.bobrov.eshop.controller;

import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.OrderDetailId;
import com.bobrov.eshop.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order_details")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailService detailService;

    @GetMapping
    public List<OrderDetail> getAll() {
        return detailService.findAll();
    }

    @GetMapping("/{id}")
    public List<OrderDetail> getDetail(@PathVariable Long id) {
        return detailService.findByOrderId(id);
    }

    @PostMapping
    public OrderDetail save(@RequestBody OrderDetail orderDetail) {
        return detailService.save(orderDetail);
    }

    @PutMapping
    public OrderDetail update(@RequestBody OrderDetail orderDetail) {
        return detailService.save(orderDetail);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody OrderDetailId id) {
        detailService.deleteById(id);
    }
}

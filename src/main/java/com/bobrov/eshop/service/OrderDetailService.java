package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.ProductJoinOrderDto;
import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.OrderDetailId;

import java.util.List;

public interface OrderDetailService {
    OrderDetail save(OrderDetail orderDetail);

    List<OrderDetail> findByOrderId(Long id);

    List<OrderDetail> findAll();

    void deleteById(OrderDetailId id);

    List<ProductJoinOrderDto> findProductsSortedFromMoreOrdersToLess();
}

package com.bobrov.eshop.dao;

import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    Optional<List<OrderDetail>> findById_OrderId(Long orderId);

    long deleteById_OrderId(Long orderId);

}

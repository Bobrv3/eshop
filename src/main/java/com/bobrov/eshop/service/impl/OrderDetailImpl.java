package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.OrderDetailRepository;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.OrderDetailId;
import com.bobrov.eshop.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailImpl implements OrderDetailService {
    private final OrderDetailRepository detailRepository;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return detailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long id) {
        return detailRepository.findById_OrderId(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<OrderDetail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public void deleteById(OrderDetailId id) {
        detailRepository.deleteById(id);
    }
}

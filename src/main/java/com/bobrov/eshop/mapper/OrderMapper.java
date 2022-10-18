package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.OrderDto;
import com.bobrov.eshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, OrderDetailMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    List<OrderDto> toListDto(List<Order> orders);
}

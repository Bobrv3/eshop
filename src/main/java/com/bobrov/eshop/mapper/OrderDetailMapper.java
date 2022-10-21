package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.OrderDetailDto;
import com.bobrov.eshop.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProductMapper.class})
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    OrderDetailDto toDto(OrderDetail detail);

    OrderDetail toDetail(OrderDetailDto dto);
}

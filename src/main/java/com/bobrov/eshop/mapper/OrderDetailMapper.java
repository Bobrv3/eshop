package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.OrderDetailDto;
import com.bobrov.eshop.model.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(uses = {ProductMapper.class})
public interface OrderDetailMapper {

    OrderDetailDto toDto(OrderDetail detail);
}

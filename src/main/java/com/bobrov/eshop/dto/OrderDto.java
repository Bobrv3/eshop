package com.bobrov.eshop.dto;

import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.model.Order;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link Order} entity
 */
@Data
public class OrderDto implements Serializable {
    private final Long id;
    private final Order.OrderStatus status;
    private final LocalDateTime createdAt;
    private final UserResponse user;
    private final Set<OrderDetailDto> orderDetails;
}
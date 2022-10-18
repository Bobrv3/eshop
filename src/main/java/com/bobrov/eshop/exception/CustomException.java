package com.bobrov.eshop.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomException {
    private String message;
    private LocalDateTime createdAt;
}

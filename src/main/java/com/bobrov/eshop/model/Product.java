package com.bobrov.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@Builder
public class Product {
    public enum ProductStatus {
        IN_STOCK, OUT_OF_STOCK, RUNNING_LOW
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @CreatedBy
    private Timestamp createdAt;

    public Product() {
        status = ProductStatus.IN_STOCK;
    }
}

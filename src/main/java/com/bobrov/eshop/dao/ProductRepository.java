package com.bobrov.eshop.dao;

import com.bobrov.eshop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    @Query("update Product p set p.status = ?1 where p.id = ?2")
    int updateStatusById(Product.ProductStatus status, Long id);

    List<Product> findByStatusNot(Product.ProductStatus status, Pageable pageable);

    Optional<Product> findByIdAndStatusNot(Long id, Product.ProductStatus status);


}

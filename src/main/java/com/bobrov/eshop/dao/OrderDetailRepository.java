package com.bobrov.eshop.dao;

import com.bobrov.eshop.dto.ProductJoinOrderDto;
import com.bobrov.eshop.model.OrderDetail;
import com.bobrov.eshop.model.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    @Query(value = """ 
            SELECT new com.bobrov.eshop.dto.ProductJoinOrderDto(p.name, p.price, sum(o.quantity) as quantity) from OrderDetail o
            left join o.product p
            group by p.name
            order by quantity desc""")
    List<ProductJoinOrderDto> findProductsSortedFromMoreOrdersToLess();

}

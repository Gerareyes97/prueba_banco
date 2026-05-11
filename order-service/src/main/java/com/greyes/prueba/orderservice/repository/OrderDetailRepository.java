package com.greyes.prueba.orderservice.repository;

import com.greyes.prueba.orderservice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}

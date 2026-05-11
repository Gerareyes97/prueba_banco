package com.greyes.prueba.orderservice.repository;

import com.greyes.prueba.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

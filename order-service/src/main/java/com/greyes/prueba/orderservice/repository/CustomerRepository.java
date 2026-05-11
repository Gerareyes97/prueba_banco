package com.greyes.prueba.orderservice.repository;

import com.greyes.prueba.orderservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

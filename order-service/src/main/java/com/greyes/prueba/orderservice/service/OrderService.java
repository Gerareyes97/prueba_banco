package com.greyes.prueba.orderservice.service;

import com.greyes.prueba.orderservice.dto.request.CreateOrderRequest;
import com.greyes.prueba.orderservice.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse cancelOrder(Long id);

    OrderResponse markOrderAsPaid(Long id);
}

package com.greyes.prueba.orderservice.service.impl;

import com.greyes.prueba.orderservice.client.ProductClient;
import com.greyes.prueba.orderservice.dto.request.*;
import com.greyes.prueba.orderservice.dto.response.*;
import com.greyes.prueba.orderservice.entity.*;
import com.greyes.prueba.orderservice.enums.OrderStatus;
import com.greyes.prueba.orderservice.exception.ResourceNotFoundException;
import com.greyes.prueba.orderservice.repository.*;
import com.greyes.prueba.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductClient productClient;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        log.info("Creando orden para el cliente: {}", request.getCustomerId());

        BigDecimal total = BigDecimal.ZERO;

        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .status(OrderStatus.PENDIENTE)
                .createdAt(LocalDateTime.now())
                .totalAmount(BigDecimal.ZERO)
                .build();

        Order savedOrder = orderRepository.save(order);

        List<OrderDetailResponse> detailResponses = new ArrayList<>();

        for (OrderItemRequest item : request.getItems()) {

            ProductResponse product = productClient.getProductById(item.getProductId());

            BigDecimal price = BigDecimal.valueOf(product.getPrice());

            BigDecimal subtotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));

            total = total.add(subtotal);

            OrderDetail detail = OrderDetail.builder()
                    .orderId(savedOrder.getId())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .price(price)
                    .build();

            orderDetailRepository.save(detail);

            detailResponses.add(
                    OrderDetailResponse.builder()
                            .productId(item.getProductId())
                            .quantity(item.getQuantity())
                            .price(price)
                            .build()
            );
        }

        savedOrder.setTotalAmount(total);

        orderRepository.save(savedOrder);

        return OrderResponse.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .status(savedOrder.getStatus())
                .createdAt(savedOrder.getCreatedAt())
                .totalAmount(savedOrder.getTotalAmount())
                .items(detailResponses)
                .build();
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));

        return mapToResponse(order);
    }

    @Override
    public OrderResponse cancelOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));

        order.setStatus(OrderStatus.CANCELADO);

        orderRepository.save(order);

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .totalAmount(order.getTotalAmount())
                .items(new ArrayList<>())
                .build();
    }

    @Override
    public OrderResponse markOrderAsPaid(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));

        order.setStatus(OrderStatus.PAGADO);

        orderRepository.save(order);

        return mapToResponse(order);
    }
}


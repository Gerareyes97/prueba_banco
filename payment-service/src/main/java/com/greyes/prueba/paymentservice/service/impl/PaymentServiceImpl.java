package com.greyes.prueba.paymentservice.service.impl;

import com.greyes.prueba.paymentservice.client.OrderClient;
import com.greyes.prueba.paymentservice.dto.PaymentRequest;
import com.greyes.prueba.paymentservice.dto.PaymentResponse;
import com.greyes.prueba.paymentservice.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final OrderClient orderClient;

    private final HttpServletRequest request;

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {

        log.info("Processing payment for order: {}", paymentRequest.getOrderId());

        String authorization = request.getHeader("Authorization");

        orderClient.markOrderAsPaid(authorization, paymentRequest.getOrderId());

        return PaymentResponse.builder()
                .orderId(paymentRequest.getOrderId())
                .status("APPROVED")
                .transactionId(UUID.randomUUID().toString())
                .build();
    }
}

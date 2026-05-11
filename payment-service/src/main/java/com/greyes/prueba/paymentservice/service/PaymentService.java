package com.greyes.prueba.paymentservice.service;

import com.greyes.prueba.paymentservice.dto.PaymentRequest;
import com.greyes.prueba.paymentservice.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}

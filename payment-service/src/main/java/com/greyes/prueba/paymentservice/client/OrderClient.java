package com.greyes.prueba.paymentservice.client;

import com.greyes.prueba.paymentservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {

    @PutMapping("/api/orders/{id}/pay")
    void markOrderAsPaid(@RequestHeader("Authorization") String authorization, @PathVariable Long id);
}
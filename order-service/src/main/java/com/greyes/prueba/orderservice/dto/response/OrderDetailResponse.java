package com.greyes.prueba.orderservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDetailResponse {

    private Long productId;

    private Integer quantity;

    private BigDecimal price;
}

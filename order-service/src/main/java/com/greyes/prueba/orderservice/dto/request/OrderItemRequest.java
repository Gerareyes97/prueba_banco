package com.greyes.prueba.orderservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {

    @NotNull(message = "El ID del producto es requerido")
    private Long productId;

    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    private Integer quantity;
}

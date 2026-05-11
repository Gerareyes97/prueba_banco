package com.greyes.prueba.orderservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @NotNull(message = "El ID del cliente es requerido")
    private Long customerId;

    @Valid
    @NotEmpty(message = "Los artículos de la orden no pueden estar vacíos")
    private List<OrderItemRequest> items;
}

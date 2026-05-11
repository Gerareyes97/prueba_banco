package com.greyes.prueba.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long id;

    private String title;

    private BigDecimal price;

    private String description;

    private String category;

    private String image;
}

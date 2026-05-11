package com.greyes.prueba.orderservice.dto.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String title;

    private Double price;

    private String description;

    private String category;

    private String image;
}

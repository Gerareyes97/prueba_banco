package com.greyes.prueba.productservice.service;

import com.greyes.prueba.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    List<String> getAllCategories();

    List<ProductResponse> getProductsByCategory(String category);
}

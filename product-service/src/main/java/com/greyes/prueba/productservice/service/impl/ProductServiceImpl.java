package com.greyes.prueba.productservice.service.impl;

import com.greyes.prueba.productservice.dto.ProductResponse;
import com.greyes.prueba.productservice.exception.ResourceNotFoundException;
import com.greyes.prueba.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final WebClient webClient;

    @Override
    public List<ProductResponse> getAllProducts() {

        log.info("Obteniendo todos los productos");

        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block();
    }

    @Override
    public ProductResponse getProductById(Long id) {

        log.info("Obteniendo producto con ID: {}", id);

        ProductResponse product = webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        return product;
    }

    @Override
    public List<String> getAllCategories() {

        log.info("Obteniendo todas las categorías");

        return webClient.get()
                .uri("/products/categories")
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {

        log.info("Obteniendo productos por categoría: {} ", category);

        return webClient.get()
                .uri("/products/category/{category}", category)
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block();
    }
}

package com.lucassneivaQikServeTask.QikServeTask.service;

import org.springframework.validation.annotation.Validated;

import com.lucassneivaQikServeTask.QikServeTask.model.Product;

import jakarta.annotation.Nonnull;

@Validated
public interface ProductService {

    @Nonnull Iterable<Product> getAllProducts();

    Product getProduct(Long id);

    Product save(Product product);
    
    Product updateProduct(Product product);
}
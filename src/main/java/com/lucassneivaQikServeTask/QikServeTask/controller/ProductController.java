package com.lucassneivaQikServeTask.QikServeTask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucassneivaQikServeTask.QikServeTask.exception.ResourceNotFoundException;
import com.lucassneivaQikServeTask.QikServeTask.model.Product;
import com.lucassneivaQikServeTask.QikServeTask.service.ProductService;

// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Nonnull;

import java.lang.module.ResolutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Api(tags="Produtos")
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
// @ApiOperation(value = "Obter um produto pelo ID", response = Product.class)
    @GetMapping(value = { "", "/" })
    public @Nonnull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
    
// @ApiOperation(value = "Adicionar um novo produto")
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    
    @PutMapping("/edit/{productId}")
    public ResponseEntity<Product> editProduct(@PathVariable Long productId, @RequestBody Product productDetails) {
        Product existingProduct = productService.getProduct(productId);

        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }
        if (productDetails.getPrice() != null) {
            existingProduct.setPrice(productDetails.getPrice());
        }
        if (productDetails.getPictureUrl() != null) {
            existingProduct.setPictureUrl(productDetails.getPictureUrl());
        }

        Product updatedProduct = productService.updateProduct(existingProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}


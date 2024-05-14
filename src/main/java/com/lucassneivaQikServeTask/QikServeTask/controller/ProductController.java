package com.lucassneivaQikServeTask.QikServeTask.controller;

import com.lucassneivaQikServeTask.QikServeTask.exception.ResourceNotFoundException;
import com.lucassneivaQikServeTask.QikServeTask.model.Product;
import com.lucassneivaQikServeTask.QikServeTask.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Nonnull;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Obter todos os produtos", description = "Retorna todos os produtos cadastrados")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Obter um produto pelo ID", description = "Retorna um produto com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "ID do produto a ser obtido", required = true) @PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Adicionar um novo produto", description = "Cria um novo produto com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(
            @Parameter(description = "Detalhes do novo produto a ser adicionado", required = true) @RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Editar um produto existente", description = "Atualiza os detalhes de um produto existente com base no ID")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "ID do produto a ser editado", required = true) @PathVariable Long id,
            @Parameter(description = "Detalhes do produto atualizado", required = true) @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setPictureUrl(updatedProduct.getPictureUrl());
        Product savedProduct = productService.updateProduct(existingProduct);
        return ResponseEntity.ok(savedProduct);
    }

    @Operation(summary = "Excluir um produto", description = "Remove um produto existente com base no ID")
    @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID do produto a ser excluído", required = true) @PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

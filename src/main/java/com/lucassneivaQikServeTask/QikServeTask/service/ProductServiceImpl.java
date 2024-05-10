package com.lucassneivaQikServeTask.QikServeTask.service;

import org.springframework.stereotype.Service;

import com.lucassneivaQikServeTask.QikServeTask.model.Product;
import com.lucassneivaQikServeTask.QikServeTask.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
	public Product getProduct(Long id) {
    	return productRepository
    	          .findById(id)
    	          .orElse(null);
    	         //.orElseThrow(() -> new ResolutionException("Product not found"));
	}

    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Product product) {
        Product existingProduct = getProduct(product.getId());

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setPictureUrl(product.getPictureUrl());
        return save(existingProduct);
    }
}

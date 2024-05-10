package com.lucassneivaQikServeTask.QikServeTask.model;

import org.springframework.lang.NonNull;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Basic(optional = false)
    private String name;

    private Double price;

    private String pictureUrl;
    
    public Product(Long id, @Nonnull() String name, Double price, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

    public Product() {
    }
}
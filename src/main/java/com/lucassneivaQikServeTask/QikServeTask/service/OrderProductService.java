package com.lucassneivaQikServeTask.QikServeTask.service;

import org.springframework.validation.annotation.Validated;

import com.lucassneivaQikServeTask.QikServeTask.model.OrderProduct;

import jakarta.annotation.Nonnull;

@Validated
public interface OrderProductService {

	OrderProduct create(@Nonnull() OrderProduct OrderProduct);
}

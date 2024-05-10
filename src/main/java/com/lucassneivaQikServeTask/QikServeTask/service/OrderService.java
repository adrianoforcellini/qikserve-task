package com.lucassneivaQikServeTask.QikServeTask.service;

import org.springframework.validation.annotation.Validated;

import com.lucassneivaQikServeTask.QikServeTask.model.Order;

import jakarta.annotation.Nonnull;

@Validated
public interface OrderService {

    @Nonnull Iterable<Order> getAllOrders();

    Order create(@Nonnull() Order order);
}
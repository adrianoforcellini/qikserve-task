package com.lucassneivaQikServeTask.QikServeTask.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucassneivaQikServeTask.QikServeTask.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

package com.lucassneivaQikServeTask.QikServeTask.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.lucassneivaQikServeTask.QikServeTask.model.Order;

import com.lucassneivaQikServeTask.QikServeTask.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());

        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}

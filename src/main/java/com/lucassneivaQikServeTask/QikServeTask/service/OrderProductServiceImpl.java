package com.lucassneivaQikServeTask.QikServeTask.service;

import org.springframework.stereotype.Service;

import com.lucassneivaQikServeTask.QikServeTask.model.OrderProduct;
import com.lucassneivaQikServeTask.QikServeTask.repository.OrderProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
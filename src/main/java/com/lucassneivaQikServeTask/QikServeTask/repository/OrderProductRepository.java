package com.lucassneivaQikServeTask.QikServeTask.repository;
import org.springframework.data.repository.CrudRepository;

import com.lucassneivaQikServeTask.QikServeTask.model.OrderProduct;
import com.lucassneivaQikServeTask.QikServeTask.model.OrderProductPK;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
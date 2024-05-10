package com.lucassneivaQikServeTask.QikServeTask.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucassneivaQikServeTask.QikServeTask.dto.OrderProductDto;
import com.lucassneivaQikServeTask.QikServeTask.exception.ResourceNotFoundException;
import com.lucassneivaQikServeTask.QikServeTask.model.Order;
import com.lucassneivaQikServeTask.QikServeTask.model.OrderProduct;
import com.lucassneivaQikServeTask.QikServeTask.model.OrderStatus;
import com.lucassneivaQikServeTask.QikServeTask.service.OrderProductService;
import com.lucassneivaQikServeTask.QikServeTask.service.OrderService;
import com.lucassneivaQikServeTask.QikServeTask.service.ProductService;

import jakarta.annotation.Nonnull;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    ProductService productService;
    OrderService orderService;
    OrderProductService orderProductService;

    public OrderController(ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }
   

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @Nonnull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
              .getProduct()
              .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        double price = order.getTotalOrderPrice(form.promoCode);
        order.setOrderPrice(price);
        
        double discount = order.getCurrencyDiscount(form.promoCode);
        order.setDiscountCurrency(discount);
        
        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
        
        private String promoCode;
        
        private String getPromoCode(){
            return promoCode;
        };
        
        private void setPromoCode(String promoCode){
        	this.promoCode = promoCode;
        };
    }
}

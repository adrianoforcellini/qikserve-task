package com.lucassneivaQikServeTask.QikServeTask.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="orderProducts")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;
    
    private Double orderPrice;
    
    private Double discountCurrency;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Double getTotalOrderPrice(String promoCode) {
        double sum = 0D;
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts) {
            sum += op.getFinalPriceWithDiscount(promoCode);
        }
        return sum;
    }

    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }
    
    public Double getCurrencyDiscount(String promoCode) {
        double sum = 0D;
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts) {
            sum += op.getCurrencyDiscount(promoCode);
        }
        return sum;
    }
}

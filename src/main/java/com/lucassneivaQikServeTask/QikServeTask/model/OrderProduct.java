package com.lucassneivaQikServeTask.QikServeTask.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class OrderProduct {
	
    private static final Map<String, Double> PROMOTIONS = new HashMap<>();
    static {
        PROMOTIONS.put("PROMO10", 0.1);
        PROMOTIONS.put("PROMO20", 0.2); 
    }

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
	private Integer quantity;
    
    public OrderProduct() {
        super();
    }

    public OrderProduct(Order order, Product product, Integer quantity) {
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }
    
    public double getFinalPriceWithDiscount(String promoCode) {
    	double price = getProduct().getPrice() * getQuantity();
        double discount = applyPromotion(promoCode);
        double finalPrice = price * (1 - discount);
        return finalPrice;
    }
    
    public double getCurrencyDiscount(String promoCode) {
    	double price = getProduct().getPrice() * getQuantity();
        double discount = applyPromotion(promoCode);
        double finalPrice = price * (1 - discount);
        return price - finalPrice;
    }
    
    public static double applyPromotion(String promoCode) {
        if (PROMOTIONS.containsKey(promoCode)) {
            return PROMOTIONS.get(promoCode);
        }
        
        return 0.0;
    }
    
}
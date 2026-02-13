package com.webtech.ordernbilling.entity;

import jakarta.persistence.*;

import com.webtech.ordernbilling.entity.Product;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_at_purchase")
    private long priceAtPurchase;

    protected OrderItem() {}

    public OrderItem(Product product, Order order, int quantity, long priceAtPurchase) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.priceAtPurchase = product.getPrice();
    }
}

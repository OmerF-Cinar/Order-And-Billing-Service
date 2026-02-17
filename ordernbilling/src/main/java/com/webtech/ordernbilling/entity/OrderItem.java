package com.webtech.ordernbilling.entity;

import jakarta.persistence.*;

import com.webtech.ordernbilling.entity.Product;

@Entity
@Table
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_at_purchase")
    private long priceAtPurchase;

    public OrderItem() {}

    public OrderItem(Product product, Order order, int quantity, long priceAtPurchase) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.priceAtPurchase = product.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(long priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}

package com.webtech.ordernbilling.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    private long orderTotal;

    protected Order() {}

    public Order(List<OrderItem> items, long orderTotal) {
        this.items = items;
        this.orderTotal = orderTotal;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public long getOrderTotal() {
        return orderTotal;
    }
}

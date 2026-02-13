package com.webtech.ordernbilling.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private long price;



    //Constructor
    public Product() {}

    public Product(String name, int amount, long price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    //getter and setter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    //toString


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}

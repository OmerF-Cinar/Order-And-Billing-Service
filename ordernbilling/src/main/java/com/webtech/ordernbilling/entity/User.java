package com.webtech.ordernbilling.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User {

    private static final int MIN_PASSWORD_LENGTH = 8;

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "accountBalance")
    private long accountBalance;

    @OneToMany
    @Column(name = "friends")
    private List<User> friends = new ArrayList<>();

    @OneToMany
    @Column(name = "favourites")
    private List<Product> favourites;

    //Constructor
    public User() {}

    public User(String username, String password, String email, long accountBalance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountBalance = accountBalance;
    }

    //getter and setter


    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Product> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Product> favourites) {
        this.favourites = favourites;
    }

}

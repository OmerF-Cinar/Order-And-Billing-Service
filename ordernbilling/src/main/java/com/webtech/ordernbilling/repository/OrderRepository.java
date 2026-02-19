package com.webtech.ordernbilling.repository;

import com.webtech.ordernbilling.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(Integer id);
}

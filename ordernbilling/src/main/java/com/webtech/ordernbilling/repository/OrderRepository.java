package com.webtech.ordernbilling.repository;

import com.webtech.ordernbilling.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

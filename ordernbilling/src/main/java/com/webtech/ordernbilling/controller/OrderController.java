package com.webtech.ordernbilling.controller;

import com.webtech.ordernbilling.DTO.CreateOrderRequestDTO;
import com.webtech.ordernbilling.entity.Order;
import com.webtech.ordernbilling.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping(path="/add/{userId}")
    public Order createOrder(@PathVariable Integer userId, @RequestBody CreateOrderRequestDTO requestDTO) {
        return orderService.createOrder(userId, requestDTO);
    }

    @GetMapping(path="/findbyuser/{userid}")
    public List<Order> findOrdersByUser(@PathVariable Integer userid) {
        return orderService.getOrdersByUser(userid);
    }
}

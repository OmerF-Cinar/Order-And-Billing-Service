package com.webtech.ordernbilling.service;

import com.webtech.ordernbilling.entity.Order;
import com.webtech.ordernbilling.entity.OrderItem;
import com.webtech.ordernbilling.entity.User;
import com.webtech.ordernbilling.repository.OrderRepository;
import com.webtech.ordernbilling.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Integer id, List<OrderItem> orderItems) {
        User reqUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User was not found."));
        List<OrderItem> currentOrderItems = orderItems.stream().filter(product ->
            productRepository.existsById(product.getId())
        ).collect(Collectors.toList());

       Long totalPrice = currentOrderItems.stream().mapToLong(OrderItem::getPriceAtPurchase).sum();

       if (reqUser.getAccountBalance() < totalPrice) {
           throw new RuntimeException("User balance must match or be greater than order total price.");
       }

       reqUser.setAccountBalance(reqUser.getAccountBalance() - totalPrice);
       Order order = new Order(currentOrderItems, totalPrice);
       orderRepository.save(order);
       return order;
    }
}

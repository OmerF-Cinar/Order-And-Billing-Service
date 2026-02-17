package com.webtech.ordernbilling.service;

import com.webtech.ordernbilling.DTO.CreateOrderRequestDTO;
import com.webtech.ordernbilling.DTO.OrderItemDTO;
import com.webtech.ordernbilling.entity.Order;
import com.webtech.ordernbilling.entity.OrderItem;
import com.webtech.ordernbilling.entity.Product;
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

    public Order createOrder(Integer id, CreateOrderRequestDTO requestDTO) {
        User reqUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User was not found."));

        Order order = new Order();
        order.setUser(reqUser);
        order.setStatus("PENDING");

        List<OrderItem> orderItems = requestDTO.getItems().stream()
                .map(itemDTO -> {

                    Product product = productRepository.findById(itemDTO.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product was not found."));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemDTO.getQuantity());
                    orderItem.setPriceAtPurchase(product.getPrice() * orderItem.getQuantity());
                    orderItem.setOrder(order);

                    return orderItem;
                }).collect(Collectors.toList());

        Long totalPrice = orderItems.stream()
                .mapToLong(OrderItem::getPriceAtPurchase).sum();

        if (reqUser.getAccountBalance() < totalPrice) {
            throw new RuntimeException("User balance must match or be greater than order total.");
        }

        userService.withdrawBalance(reqUser.getId(), totalPrice);

        order.setOrderTotal(totalPrice);
        order.setItems(orderItems);

        return orderRepository.save(order);
    }
}

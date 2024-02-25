package com.bhishma.orderservice.service;

import com.bhishma.orderservice.entity.Order;
import com.bhishma.orderservice.response.OrderResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<Order> createOrder(Order order);
    ResponseEntity<List<OrderResponse>> getOrdersByUserId(long userId);
}

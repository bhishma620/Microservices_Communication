package com.bhishma.order_service.service;

import com.bhishma.order_service.entity.Order;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface OrderService {

   Mono<String> addOrder(Order order);

   Mono<Order> getOrderDetails(int id);

   Mono<String> updateOrderDetails(int id,Order order);

    Mono<String> deleteOrder(int id);
}

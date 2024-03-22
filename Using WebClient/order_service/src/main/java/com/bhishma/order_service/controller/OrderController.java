package com.bhishma.order_service.controller;

import com.bhishma.order_service.entity.Order;
import com.bhishma.order_service.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-service/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    Mono<String> addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }


    @GetMapping("/order/{id}")
    Mono<Order> getOrderDetails(@PathVariable("id") int id) {
        return orderService.getOrderDetails(id);
    }

    @PutMapping("/order/{id}")
  Mono<String> updateOrderDetails(@PathVariable("id") int id
            , @RequestBody Order order) {
        return orderService.updateOrderDetails(id, order);
    }

    @DeleteMapping("/order/{id}")
    Mono<String> deleteOrder(@PathVariable("id") int id) {
        return orderService.deleteOrder(id);
    }
}

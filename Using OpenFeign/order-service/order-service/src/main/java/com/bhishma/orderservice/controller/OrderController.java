package com.bhishma.orderservice.controller;

import com.bhishma.orderservice.entity.Order;
import com.bhishma.orderservice.feign_client.DeliveryFeign;
import com.bhishma.orderservice.response.OrderResponse;
import com.bhishma.orderservice.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public  String home(){
        return "Welcome to Order Service !!";
    }

    //creating order
    @PostMapping("/order")
    public ResponseEntity<Order>createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    //Get orders by UserID as param
    @GetMapping("/order")
    public  ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathParam("userId") long userId){

        return orderService.getOrdersByUserId(userId);

    }



}

package com.bhishma.orderservice.service.Impl;

import com.bhishma.orderservice.entity.Order;
import com.bhishma.orderservice.feign_client.DeliveryFeign;
import com.bhishma.orderservice.repository.OrderRepo;
import com.bhishma.orderservice.request.DeliveryRequest;
import com.bhishma.orderservice.response.DeliveryResponse;
import com.bhishma.orderservice.response.OrderResponse;
import com.bhishma.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderSeviceImpl implements OrderService {
    @Autowired
   private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    DeliveryFeign deliveryFeign;

    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        Order savedOrder=orderRepo.save(order);

        DeliveryRequest deliveryRequest=new DeliveryRequest();
        deliveryRequest.setOrderId(order.getId());
        deliveryRequest.setShipperId(order.getShipperId());
        deliveryRequest.setOrderStatus("Order Placed");

        deliveryFeign.addDeliveryStatus(deliveryRequest);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(long userId) {

        System.out.println(userId);
       List<Order>orders=orderRepo.findAllByUserId(userId);

       List<OrderResponse>orderResponses= orders.stream()
                                                .map(
                                                        order -> {
                                                            OrderResponse response = modelMapper.map(order, OrderResponse.class);
                                                            DeliveryResponse deliveryResponse=deliveryFeign.getDetailsByOrderId(order.getId());
                                                            response.setDeliveryResponse(deliveryResponse);
                                                            return response;
                                                        }
                                                )
                                                .collect(Collectors.toList());

       return new ResponseEntity<>(orderResponses,HttpStatus.OK);

    }
}

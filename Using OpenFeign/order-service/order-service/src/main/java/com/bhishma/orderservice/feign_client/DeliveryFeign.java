package com.bhishma.orderservice.feign_client;

import com.bhishma.orderservice.request.DeliveryRequest;
import com.bhishma.orderservice.response.DeliveryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="delivery-service",url="http://localhost:8081/delivery/api")
public interface DeliveryFeign {
    @GetMapping("/home")
    public String home();

    @GetMapping("/delivery/{orderId}")
    public DeliveryResponse getDetailsByOrderId(@PathVariable("orderId") long orderId);

    @PostMapping("/delivery")
    public void addDeliveryStatus(@RequestBody DeliveryRequest deilveryStatus );
}

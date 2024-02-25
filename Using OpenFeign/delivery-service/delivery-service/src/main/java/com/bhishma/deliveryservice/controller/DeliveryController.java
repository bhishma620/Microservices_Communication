package com.bhishma.deliveryservice.controller;

import com.bhishma.deliveryservice.entity.DeliveryStatus;
import com.bhishma.deliveryservice.response.DeliveryStatusResponse;
import com.bhishma.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery/api")

public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/home")
    public  String home(){
        return "Welcome to Delivery Service !!";
    }

    @PostMapping("/delivery")
    public void addDeliveryStatus(@RequestBody DeliveryStatus deilveryStatus ){

        deliveryService.addDeliveryStatus(deilveryStatus);

    }


    @PutMapping("/delivery/{orderId}")
    public String updateDeliveryDetails(@PathVariable("orderId") long orderId,
                                      @RequestBody DeliveryStatusResponse details){

        return  deliveryService.updateStatus(orderId,details);
    }

    @GetMapping("/delivery/{orderId}")
    public  DeliveryStatusResponse getDetailsByOrderId(@PathVariable("orderId") long orderId){
        return deliveryService.getDetailsByOrderId(orderId);
    }



}

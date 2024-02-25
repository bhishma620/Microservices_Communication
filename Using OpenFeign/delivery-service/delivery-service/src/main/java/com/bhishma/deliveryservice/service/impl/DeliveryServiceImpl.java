package com.bhishma.deliveryservice.service.impl;

import com.bhishma.deliveryservice.entity.DeliveryStatus;
import com.bhishma.deliveryservice.repository.DeliveryRepo;
import com.bhishma.deliveryservice.response.DeliveryStatusResponse;
import com.bhishma.deliveryservice.service.DeliveryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepo deliveryRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String updateStatus(long id, DeliveryStatusResponse details) {

        Optional<DeliveryStatus> deliveryStatusOptional = deliveryRepo.findById(id);

        if (deliveryStatusOptional.isPresent()) {
            DeliveryStatus deliveryStatus = deliveryStatusOptional.get();

            deliveryStatus.setOrderStatus(details.getOrderStatus());

            deliveryRepo.save(deliveryStatus);

            return "Delivery status updated successfully";
        }
            return "Delivery status not found for ID: " + id;


    }

    @Override
    public void addDeliveryStatus(DeliveryStatus deliveryStatus) {
        deliveryRepo.save(deliveryStatus);
    }

    @Override
    public DeliveryStatusResponse getDetailsByOrderId(long orderId) {
        DeliveryStatus deliveryStatus=deliveryRepo.findByOrderId(orderId);
        return modelMapper.map(deliveryStatus,DeliveryStatusResponse.class);
    }


}

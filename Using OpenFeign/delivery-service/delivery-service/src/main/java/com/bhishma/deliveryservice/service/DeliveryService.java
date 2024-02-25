package com.bhishma.deliveryservice.service;

import com.bhishma.deliveryservice.entity.DeliveryStatus;
import com.bhishma.deliveryservice.response.DeliveryStatusResponse;

public interface DeliveryService {
    String updateStatus(long id , DeliveryStatusResponse details);

    void addDeliveryStatus(DeliveryStatus deliveryStatus);

    DeliveryStatusResponse getDetailsByOrderId(long orderId);
}

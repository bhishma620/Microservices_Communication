package com.bhishma.orderservice.request;

import lombok.Data;

@Data
public class DeliveryRequest {
    private long orderId;

    private long shipperId;

    private String orderStatus;
}

package com.bhishma.orderservice.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OrderResponse {
        private long id;
        private long userId;
        private long productId;
        private long shipperId;
        private String purchaseDate;

        private DeliveryResponse deliveryResponse;


}

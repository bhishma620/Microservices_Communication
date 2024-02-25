package com.bhishma.deliveryservice.repository;

import com.bhishma.deliveryservice.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<DeliveryStatus,Long> {

    DeliveryStatus findByOrderId(long orderId);
}

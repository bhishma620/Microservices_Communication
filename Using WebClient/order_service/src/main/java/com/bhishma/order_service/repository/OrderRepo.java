package com.bhishma.order_service.repository;

import com.bhishma.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {

}

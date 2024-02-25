package com.bhishma.orderservice.repository;

import com.bhishma.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {

    List<Order> findAllByUserId(long userId);


}

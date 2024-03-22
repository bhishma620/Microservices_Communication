package com.bhishma.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_data")
public class Order {
    @Id
    private  int id;
    private  int productId;
    private  int noOfItem;
}

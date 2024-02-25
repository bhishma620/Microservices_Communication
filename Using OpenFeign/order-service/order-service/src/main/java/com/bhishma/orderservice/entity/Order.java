package com.bhishma.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OrderDetails")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private long userId;
private long productId;
private long shipperId;
private String purchaseDate;

}

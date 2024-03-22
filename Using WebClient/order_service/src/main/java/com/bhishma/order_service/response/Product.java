package com.bhishma.order_service.response;

import lombok.Data;

@Data
public class Product {
    private  int id;
    private String name;
    private String category;
    private int noOfProduct;
}

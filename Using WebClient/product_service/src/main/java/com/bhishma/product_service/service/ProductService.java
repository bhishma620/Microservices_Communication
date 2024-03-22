package com.bhishma.product_service.service;

import com.bhishma.product_service.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    ResponseEntity<String> addProduct(Product product);

    ResponseEntity<Product> getProduct(int id);

    ResponseEntity<Product> updateProduct(int id,
                                          @RequestBody Product product);

    ResponseEntity<List<Product>> getAllProduct();

    ResponseEntity<String> deleteProduct(int id);

//    ResponseEntity<Product> updateProductCount(int id,int noOfItem);
}

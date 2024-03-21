package com.bhishma.product_service.controller;

import com.bhishma.product_service.entity.Product;
import com.bhishma.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service/api")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("")
    ResponseEntity<String> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<Product> getProduct(@PathVariable("id") int id){
       return productService.getProduct(id);
    }

    @PutMapping("/product/{id}")
    ResponseEntity<Product> updatetProduct(@PathVariable("id") int id,
                                           @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @GetMapping("/product")

    ResponseEntity<List<Product>> getAllProduct(){

        return productService.getAllProduct();
    }

    @DeleteMapping("/product/{id}")
    ResponseEntity<String> deleteProduct ( @PathVariable("id") int id){
     return productService.deleteProduct(id);
    }



}

package com.bhishma.product_service.service;


import com.bhishma.product_service.entity.Product;
import com.bhishma.product_service.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public ResponseEntity<String> addProduct(Product product) {
        productRepo.save(product);

        return new ResponseEntity<>(
                "Product With Id:"+product.getId()+" Successfully added.",
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> getProduct(int id) {

        Optional<Product> product= productRepo.findById(id);

        if(product.isPresent()){
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Product(),HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<Product> updateProduct(int id,
                                                 @RequestBody Product newProduct){

        Optional<Product> product= productRepo.findById(id);

        if(product.isPresent()){
            Product p=product.get();

            p.setCategory(newProduct.getCategory());
            p.setName(newProduct.getName());
            p.setNoOfProduct(newProduct.getNoOfProduct());

            productRepo.save(p);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }



        return new ResponseEntity<>(new Product(),HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {

        List<Product>products=productRepo.findAll();

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteProduct(int id) {

        Optional<Product> product= productRepo.findById(id);

        if(product.isPresent()){

            productRepo.delete(product.get());

            return new ResponseEntity<>("Product with id "+
                    id+" Deleted Successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>("No Product find with id "+id,
                HttpStatus.NOT_FOUND);

    }

//    @Override
//    public ResponseEntity<Product> updateProductCount(int id, int noOfItem) {
//
//        Optional <Product> product=productRepo.findById(id);
//        if(product.isPresent()){
//            product.get().setNoOfProduct(noOfItem);
//            productRepo.save(product.get());
//            return new ResponseEntity<Product>(product.get(),HttpStatus.OK);
//        }
//        return new ResponseEntity<Product>(new Product(),HttpStatus.NOT_FOUND);
//    }


}

package com.bhishma.order_service.service;


import com.bhishma.order_service.entity.Order;
import com.bhishma.order_service.repository.OrderRepo;
import com.bhishma.order_service.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    WebClient webClient;
    @Autowired
    OrderRepo orderRepo;

    @Override
    public Mono<String> addOrder(Order order) {
        return webClient.get()
                .uri("/product/" + order.getProductId())
                .retrieve()
                .bodyToMono(Product.class)
                .flatMap(product -> {
                    if (product.getNoOfProduct() >= order.getNoOfItem()) {
                        product.setNoOfProduct(product.getNoOfProduct() - order.getNoOfItem());
                        return webClient.put()
                                .uri("/product/" + order.getProductId())
                                .body(Mono.just(product), Product.class)
                                .retrieve()
                                .toBodilessEntity()
                                .then(Mono.just(order))
                                .flatMap(savedOrder -> {
                                    orderRepo.save(savedOrder);
                                    return Mono.just("Order Placed!!");
                                });
                    } else {
                        return Mono.just("Product is not available");
                    }
                })
                .onErrorResume(throwable -> {
                    if (throwable instanceof WebClientResponseException.NotFound) {
                        return Mono.error(new RuntimeException("Product not found"));
                    }
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<Order> getOrderDetails(int id) {

        Optional<Order> order = orderRepo.findById(id);
        return order.map(Mono::just).orElseGet(() -> Mono.just(new Order()));
    }

    @Override
    public Mono<String> updateOrderDetails(int id, Order order) {

        Optional<Order> previousOrder =
                orderRepo.findById(id);

        if (previousOrder.isPresent()) {
            int dif = order.getNoOfItem()
                    - previousOrder.get().getNoOfItem();

           return  webClient.get()
                    .uri("/product/" + order.getProductId())
                    .retrieve()
                    .bodyToMono(Product.class)
                    .flatMap(
                            product -> {



                                    if (product.getNoOfProduct() >= dif) {

                                        Order curOrder = previousOrder.get();
                                        curOrder.setNoOfItem(order.getNoOfItem());
                                        orderRepo.save(curOrder);

                                        product.setNoOfProduct(
                                                product.getNoOfProduct() - dif
                                        );



                                        webClient.put()
                                                .uri("/product/{id}", order.getProductId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(product)
                                                .retrieve()
                                                .bodyToMono(Product.class)
                                                .subscribe(updatedProduct -> {

                                                });



                                        return Mono.just("Order with id " + id + " Updated");

                                    }


                                        return Mono.just("Required Product is not available!!");

                            }
                    );
        }

            return Mono.just("NO order found with Id " + id);


        }

        @Override
        public Mono<String> deleteOrder ( int id){

            Optional<Order> order =
                    orderRepo.findById(id);

            if (order.isPresent()) {

                orderRepo.deleteById(id);

                webClient.get()
                        .uri("/product/" + id)
                        .retrieve()
                        .bodyToMono(Product.class)
                        .flatMap(
                                product -> {
                                    product.setNoOfProduct(
                                            product.getNoOfProduct()+order.get().getNoOfItem()
                                    );
                                    webClient.put().uri("/product/" + order.get().getProductId(), product);

                                    return Mono.just("Order with id"+id+" Cancelled.");
                                }
                        );


            }
            return Mono.just("NO order found with Id " + id);

        }

    }

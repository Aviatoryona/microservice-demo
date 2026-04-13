package com.mcsdemo.orderservice.service;

import com.mcsdemo.orderservice.dto.ProductResponse;
import com.mcsdemo.orderservice.model.Order;
import com.mcsdemo.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final WebClient webClient;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public Order createOrder(Long productId, Integer quantity) {

        ProductResponse product = webClient.get()
                .uri(productServiceUrl + "/products/" + productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        double total = product.getPrice() * quantity;

        Order order = Order.builder()
                .productId(productId)
                .quantity(quantity)
                .totalPrice(total)
                .build();

        return repository.save(order);
    }
}


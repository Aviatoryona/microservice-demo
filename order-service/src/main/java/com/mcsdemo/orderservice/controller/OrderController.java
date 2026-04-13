package com.mcsdemo.orderservice.controller;

import com.mcsdemo.orderservice.model.Order;
import com.mcsdemo.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order create(
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        return service.createOrder(productId, quantity);
    }
}


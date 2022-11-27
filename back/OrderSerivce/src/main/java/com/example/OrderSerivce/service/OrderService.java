package com.example.OrderSerivce.service;

import com.example.OrderSerivce.dto.OrderDto;
import com.example.OrderSerivce.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}

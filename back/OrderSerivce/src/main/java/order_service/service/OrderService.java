package order_service.service;

import order_service.dto.OrderDto;
import order_service.jpa.Order;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<Order> getOrdersByUserId(String userId);
}

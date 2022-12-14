package order_service.service;

import order_service.dto.OrderDto;
import order_service.jpa.Order;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    List<Order> getOrderListbyUserIdx(Long userIdx);

}

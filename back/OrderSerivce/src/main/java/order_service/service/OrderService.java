package order_service.service;

import order_service.dto.OrderCatalogUserDto;
import order_service.dto.OrderDto;
import order_service.jpa.Order;

import java.util.List;

public interface OrderService {
//    OrderDto createOrder(OrderDto orderDetails);
    List<Order> getOrderListbyUserIdx(Long userIdx);

    OrderCatalogUserDto getOrder(Long orderIdx);

    Order createOrder(Long userIdx, Long catalogIdx, Integer qty, Integer price);
}

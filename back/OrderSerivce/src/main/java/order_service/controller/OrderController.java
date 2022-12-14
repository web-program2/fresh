package order_service.controller;

import lombok.RequiredArgsConstructor;
import order_service.dto.CreateOrderDTO;
import order_service.dto.OrderCatalogUserDto;
import order_service.jpa.Order;
import order_service.service.OrderService;
import order_service.vo.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;


    //userIdx requestbody
    @PostMapping("/orders")
    public List<Order> getOrderList(@RequestBody ResponseUser responseUser) {
        List<Order> orderList = orderService.getOrderListbyUserIdx(responseUser.getUserIdx());
        return orderList;
    }
    @PostMapping("/order")
    public Order createOrder(@RequestBody CreateOrderDTO createOrderDTO){
        return orderService.createOrder(createOrderDTO.getUserIdx(), createOrderDTO.getCatalogIdx(),
                createOrderDTO.getQty(), createOrderDTO.getPrice());
    }
    @PostMapping("/{orderIdx}")
    public OrderCatalogUserDto getOrder(@PathVariable Long orderIdx) {
        OrderCatalogUserDto orderCatalogUserDto = orderService.getOrder(orderIdx);
        return orderCatalogUserDto;
    }
}

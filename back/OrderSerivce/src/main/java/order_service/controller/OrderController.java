package order_service.controller;

import lombok.RequiredArgsConstructor;
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
    @GetMapping("/order_list")
    public List<Order> getOrderList(@RequestBody ResponseUser responseUser) {
        List<Order> orderList = orderService.getOrderListbyUserIdx(responseUser.getUserIdx());
        return orderList;
    }
//    @GetMapping("/getOrderList")
//    public OrderCatalogUserDto getOrder() {
//
//    }
}
